package it.univr.WeatherStation;

import it.univr.WeatherStation.Sensor.Battery;
import it.univr.WeatherStation.Sensor.Sensor;
import it.univr.WeatherStation.Sensor.SensorBrokenException;
import it.univr.WeatherStation.Server.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

public class Station extends Thread {

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
    private JSONObject errors;
    private boolean sendedBatteryLow;

    private boolean running = true;


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
        errors = new JSONObject();
        sendedBatteryLow = false;

        start();
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
                errors = jsonErr;
                errors.put("sensorsBroken", err);
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
        try {
            if (windSensor.getValue() > 40 || temperatureSensor.getValue() < -10) {
                closeGenerator();
            } else {
                openGenerator();
            }
        } catch (SensorBrokenException ex) {
        }
    }

    private void checkAndSendErrors() {
        if (errors.length() == 0)
            return;
        maintenanceServer.sendData(errors);
        errors = new JSONObject();
    }


    private void checkBattery() {
        try {
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
        } catch (SensorBrokenException e) {
        }
    }

    private void stopStation() {
        closeGenerator();
        interrupt();
        running = false;
    }

    private void waitDataServer() {
        if (dataServer.isWaiting())
            sendData(true);
    }

    private void waitMaintenanceServer() {
        if (maintenanceServer.isWaiting())
            sendState();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (running) {
            if (!energySaving) {
                elapsedTime = System.currentTimeMillis();
                if (elapsedTime - startTime >= 1 * 60 * 1000) {
                    sendData(false);
                    startTime = elapsedTime;
                }
                waitDataServer();
                waitMaintenanceServer();
                checkAndSendErrors();
            }
            extremeWeatherConditions();
            checkBattery();
        }
        return;
    }

    private void openGenerator() {
        if(isCharging())
            return;
        solarPanel.setOpen(true);
        windTurbine.setOpen(true);
        sendState();
    }

    private void closeGenerator() {
        if(!isCharging())
            return;
        solarPanel.setOpen(false);
        windTurbine.setOpen(false);
        sendState();
    }

    private boolean isCharging() {
        return windTurbine.isOpen() || solarPanel.isOpen();
    }

    public boolean isRunning(){
        return running;
    }
}


