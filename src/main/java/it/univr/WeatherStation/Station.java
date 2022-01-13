package it.univr.WeatherStation;//import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Station extends Thread{

    private int id;
    private Battery batteryLevel;
    private Sensor windSensor;
    private Sensor temperatureSensor;
    private Sensor humiditySensor;
    private Sensor lightSensor;
    private Generator solarPanel;
    private Generator windTurbine;
    private boolean energySaving;
    private Server dataServer;
    private Server maintenanceServer;
    private List<String> errors;


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
        errors = new ArrayList<>();

        start();
    }

    private void sendData(){
        dataServer.sendData(readSensorsData());
    }

    private String readSensorsData(){
        return "{ \"timestamp\": " + new Timestamp(System.currentTimeMillis()) + ", "
                + "\"station\": " + id + ", "
                + "\"wind\": \"" + windSensor.getValue() + " km/h\", "
                + "\"temperature\": \"" + temperatureSensor.getValue() + "°C\", "
                + "\"light\": \"" + lightSensor.getValue() + " lm\", "
                + "\"humidity\": \"" + humiditySensor.getValue() + "%\"}";
    }

    private void sendState(){
        maintenanceServer.sendData(getStationState());
    }

    private String getStationState(){
        /*return "Station " + id + ":"
                + "\nbatteryLevel: " + getBatteryLevel()
                + "\nisRunning: " + isRunning()
                + "\nisCharging: " + isCharging()
                + "\nenergySaving: " + energySaving;*/
        return "{ \"timestamp\": " + new Timestamp(System.currentTimeMillis()) + ", "
                + "\"station\": " + id + ", "
                + "\"batteryLevel\": \"" + batteryLevel.getLevel() + "%\", "
                + "\"isRunning\": " + isRunning() + ", "
                + "\"isCharging\": " + isCharging() + ", "
                + "\"energySaving\": " + energySaving + "}";
    }

    private void extremeWeatherConditions() throws SensorBrokenException{
        if(windSensor.getValue() > 40 || temperatureSensor.getValue() < -10){
            solarPanel.setOpen(false);
            windTurbine.setOpen(false);
        } else {
            solarPanel.setOpen(true);
            windTurbine.setOpen(true);
        }
    }

    private void checkAndSendErrors(){
        if(errors.isEmpty())
            return;
        for (String error: errors) {
            System.out.println(error);
        }
    }

    private void checkBattery(){
        if(batteryLevel.getLevel() < 20){
            energySaving = true;
            sendState();
            if(batteryLevel.getLevel() < 2){
                sendState();
                stopStation();
            }
        }
    }

    private void stopStation() {
        interrupt();
    }

    private void waitDataServer(){
        if (dataServer.isWaiting())
            sendData();
    }

    private void waitMaintenanceServer(){
        if (maintenanceServer.isWaiting())
            sendState();
    }

    @Override
    public void run() {
        while (true) {
            if (!energySaving) {
                waitDataServer();
                waitMaintenanceServer();
                checkAndSendErrors();
            }
            checkBattery();
            extremeWeatherConditions();
        }
    }

    private boolean isCharging(){
        return windTurbine.isOpen() || solarPanel.isOpen();
    }

    private boolean isRunning(){
        return !isInterrupted();
    }
}


