package it.univr.WeatherStation;//import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Station extends Thread{

    private Battery batteryLevel;  // TODO: Possibilità di fare oggetto a parte (mock)
    private Sensor windSensor;
    private Sensor temperatureSensor;
    private Sensor humiditySensor;
    private Sensor lightSensor;
    private Generator solarPanel;
    private Generator windTurbine;
    private boolean energySaving;
    private List<String> errors;


    /**
     * @param windSensor
     * @param temperatureSensor
     * @param humiditySensor
     * @param lightSensor
     */
    public Station(Sensor windSensor, Sensor temperatureSensor, Sensor humiditySensor, Sensor lightSensor, Battery batteryLevel) {
        this.windSensor = windSensor;
        this.temperatureSensor = temperatureSensor;
        this.humiditySensor = humiditySensor;
        this.lightSensor = lightSensor;
        this.batteryLevel = batteryLevel;

        solarPanel = new Generator(true);
        windTurbine = new Generator(true);
        energySaving = false;
        errors = new ArrayList<>();

        start();
    }

    public void sendData(){
        System.out.println("readSensorsData()");
    }

    /*public JSONObject readSensorsData(){
        JSONObject obj = new JSONObject();
        /*obj.put("wind", windSensor.getValue() + " km/h");
        obj.put("temperature", temperatureSensor.getValue() + " °C");
        obj.put("humidity", humiditySensor.getValue() + "%");
        obj.put("light", lightSensor.getValue() + " lm");*/
        /*return obj;

    }*/

    public void extremeWeatherConditions(){
        if(windSensor.getValue() > 40 || temperatureSensor.getValue() < -10){ // TODO: VALORI DA SISTEMARE
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
        System.out.println();
    }

    public void checkBattery(){
        if(batteryLevel.getLevel() < 20){
            energySaving = true;
            sendState();
            if(batteryLevel.getLevel() < 2){
                sendState(); // TODO: da modificare state o metodo
                // TODO: exit(1)
            }
        }
    }

    public void waitServer(){

    }

    @Override
    public void run() {
        while (true) {
            if (!energySaving) {
                // TODO: aaaa
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
        return batteryLevel.getLevel();
    }

    public boolean isCharging(){
        return windTurbine.isOpen() || solarPanel.isOpen();
    }
}


