package it.univr.WeatherStation;//import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Station extends Thread{

    private Sensor batteryLevel;
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


    public Station(Sensor windSensor, Sensor temperatureSensor, Sensor humiditySensor, Sensor lightSensor, Battery batteryLevel, Server dataServer, Server maintenanceServer) {
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

    public void sendData(){
        dataServer.sendData(readSensorsData());
    }

    public String readSensorsData(){
        // TODO: Aggiungi dati sensori
        return "a";
    }

    public void extremeWeatherConditions(){
        if(windSensor.getValue() > 40 || temperatureSensor.getValue() < -10){
            solarPanel.setOpen(false);
            windTurbine.setOpen(false);
        } else {
            solarPanel.setOpen(true);
            windTurbine.setOpen(true);
        }
    }

    public void checkAndSendErrors(){
        if(errors.isEmpty())
            return;
        for (String error: errors) {
            System.out.println(error);
        }
    }

    public void sendState(){
        maintenanceServer.sendData(getStationState());
    }

    public void checkBattery(){
        if(batteryLevel.getValue() < 20){
            energySaving = true;
            sendState();
            if(batteryLevel.getValue() < 2){
                sendState();
                stopStation();
            }
        }
    }

    private void stopStation() {
        interrupt();
    }

    public void waitDataServer(){
        if (dataServer.isWaiting())
            sendData();
    }

    public void waitMaintenanceServer(){
        if (maintenanceServer.isWaiting())
            sendState();
    }

    @Override
    public void run() {
        while (true) {
            if (!energySaving) {
                // TODO: aaaa
                waitDataServer();
                waitMaintenanceServer();
                checkAndSendErrors();
            }
            checkBattery();
            extremeWeatherConditions();
        }
    }


    public boolean isEnergySaving() {
        return energySaving;
    }

    public int getBatteryLevel() {
        return batteryLevel.getValue();
    }

    public boolean isCharging(){
        return windTurbine.isOpen() || solarPanel.isOpen();
    }

    private String getStationState(){
        return "Station:"
                + "\nbatteryLevel: " + getBatteryLevel()
                + "\nisRunning: " + !isInterrupted()
                + "\nisCharging: " + isCharging()
                + "\nenergySaving: " + isEnergySaving();
    }
}


