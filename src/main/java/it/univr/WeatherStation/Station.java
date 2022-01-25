package it.univr.WeatherStation;

import it.univr.WeatherStation.Sensor.Battery;
import it.univr.WeatherStation.Sensor.Sensor;
import it.univr.WeatherStation.Sensor.SensorBrokenException;
import it.univr.WeatherStation.Sensor.SensorErrors;
import it.univr.WeatherStation.Server.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Station {

    private final int id;
    private final Battery batteryLevel;
    private final Sensor windSensor;
    private final Sensor temperatureSensor;
    private final Sensor humiditySensor;
    private final Sensor lightSensor;
    private final Generator solarPanel;
    private final Generator windTurbine;
    private boolean energySaving;
    private final Server dataServer;
    private final Server maintenanceServer;
    private final SensorErrors errors;
    private boolean sendedBatteryLow;

    private boolean running = true;
    private Timer timerSendData;
    private ThreadPoolExecutor executor;


    public Station(int id, Sensor windSensor, Sensor temperatureSensor, Sensor humiditySensor, Sensor lightSensor, Battery batteryLevel, Server dataServer, Server maintenanceServer) {
        this.id = id;
        this.windSensor = windSensor;
        this.temperatureSensor = temperatureSensor;
        this.humiditySensor = humiditySensor;
        this.lightSensor = lightSensor;
        this.batteryLevel = batteryLevel;
        this.dataServer = dataServer;
        this.maintenanceServer = maintenanceServer;

        solarPanel = new Generator(true);
        windTurbine = new Generator(true);
        energySaving = false;
        errors = new SensorErrors();
        sendedBatteryLow = false;

        timerSendData = new Timer();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        startStation();
    }

    private void sendData(boolean manualRequest) {
        dataServer.sendData(readSensorsData(manualRequest));
    }

    private JSONObject readSensorsData(boolean manualRequest) {
        JSONObject json = new JSONObject();
        JSONObject jsonErr = new JSONObject();
        JSONArray err = new JSONArray();
        try {
            json.put("timestamp", new Timestamp(System.currentTimeMillis()));
            json.put("station", id);
            jsonErr.put("timestamp", new Timestamp(System.currentTimeMillis()));
            jsonErr.put("station", id);
            try {
                json.put("wind", windSensor.getValue() + " km/h");
            } catch (SensorBrokenException ex) {
                err.put("wind");
            }
            try {
                json.put("temperature", temperatureSensor.getValue() + " °C");
            } catch (SensorBrokenException ex) {
                err.put("temperature");
            }
            try {
                json.put("light", lightSensor.getValue() + " lm");
            } catch (SensorBrokenException ex) {
                err.put("light");
            }
            try {
                json.put("humidity", humiditySensor.getValue() + "%");
            } catch (SensorBrokenException ex) {
                err.put("humidity");
            }

            if (err.length() > 0) {
                jsonErr.put("sensorsBroken", err);
                errors.pushErrors(jsonErr);
            }

            json.put("manualRequest", manualRequest);
        } catch (JSONException jex) {
        }
        return json;
    }

    private void sendState() {
        maintenanceServer.sendData(getStationState());
    }

    private JSONObject getStationState() {
        JSONObject json = new JSONObject();
        try {
            json.put("timestamp", new Timestamp(System.currentTimeMillis()));
            json.put("station", id);
            json.put("batteryLevel", batteryLevel.getValue() + "%");
            json.put("isCharging", isCharging());
            json.put("energySaving", energySaving);
        } catch (JSONException | SensorBrokenException ex) {
        }
        return json;
    }

    private void extremeWeatherConditions() {
        synchronized (windSensor) {
            try {
                windSensor.wait();
                if (windSensor.getValue() > 40) {
                    closeGenerator();
                } else {
                    openGenerator();
                }
            } catch (InterruptedException | SensorBrokenException e) {

            }
        }
    }

    private void checkAndSendErrors() {
        synchronized (errors) {
            try {
                errors.wait();
                if (!energySaving) {
                    maintenanceServer.sendData(errors.popErrors());
                }
            } catch (InterruptedException e) {
                stopStation();
            }
        }
    }


    private void checkBattery() {
        synchronized (batteryLevel) {
            try {
                batteryLevel.wait();
                if (batteryLevel.getValue() < 2) {
                    stopStation();
                } else if (batteryLevel.getValue() < 20) {
                    if (sendedBatteryLow)
                        return;
                    energySaving = true;
                    sendState();
                    sendedBatteryLow = true;
                } else {
                    energySaving = false;
                    sendedBatteryLow = false;
                }
            } catch (InterruptedException | SensorBrokenException e) {

            }
        }
    }

    private void stopStation() {
        if (!running)
            return;
        running = false;
        closeGenerator();
        timerSendData.cancel();
        executor.shutdown();
        JSONObject stopMessage = new JSONObject();
        try {
            stopMessage.put("timestamp", new Timestamp(System.currentTimeMillis()));
            stopMessage.put("station", id);
            stopMessage.put("error", "Station is stopping");
        } catch (JSONException e) {

        }
        maintenanceServer.sendData(stopMessage);
    }

    private void waitDataServer() {
        try {
            dataServer.waitServer();
            if (!energySaving) {
                sendData(true);
            }
        } catch (InterruptedException e) {
            stopStation();
        }
    }

    private void waitMaintenanceServer() {
        try {
            maintenanceServer.waitServer();
            if (!energySaving) {
                sendState();
            }
        } catch (InterruptedException e) {
            stopStation();
        }
    }


    private void startStation() {
        int interval = 60 * 1000;
        timerSendData.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!energySaving) {
                    sendData(false);
                }
            }
        }, interval, interval);

        executor.submit(() -> {
            while (running) {
                waitDataServer();
            }
        });
        executor.submit(() -> {
            while (running) {
                waitMaintenanceServer();
            }
        });
        executor.submit(() -> {
            while (running) {
                extremeWeatherConditions();
            }
        });
        executor.submit(() -> {
            while (running) {
                checkBattery();
            }
        });
        executor.submit(() -> {
            while (running) {
                checkAndSendErrors();
            }
        });
    }

    private void openGenerator() {
        if (isCharging())
            return;
        solarPanel.setOpen(true);
        windTurbine.setOpen(true);
        sendState();
    }

    private void closeGenerator() {
        if (!isCharging())
            return;
        solarPanel.setOpen(false);
        windTurbine.setOpen(false);
        sendState();
    }

    private boolean isCharging() {
        return windTurbine.isOpen() || solarPanel.isOpen();
    }

    public boolean isRunning() {
        return running;
    }
}


