package it.univr.WeatherStation;

import it.univr.WeatherStation.Sensor.*;
import it.univr.WeatherStation.Server.Server;
import it.univr.WeatherStation.Server.ServerData;
import it.univr.WeatherStation.Server.ServerMaintenance;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    private WindSensor windSensor = new WindSensor(0);
    private TemperatureSensor temperatureSensor = new TemperatureSensor(19);
    private Battery batteryLevel = new Battery(30);
    private ServerData dataServer = new ServerData();
    private ServerMaintenance maintenanceServer = new ServerMaintenance();
    private Station weatherStation = new Station(1234, windSensor, temperatureSensor, new Sensor(50), new Sensor(50000), batteryLevel, dataServer, maintenanceServer);


    @RequestMapping("/")
    public String home(Model model) {
        try {
            model.addAttribute("battery", batteryLevel.getValue());
        } catch (SensorBrokenException e) {
            //e.printStackTrace();
        }
        try {
            model.addAttribute("wind", windSensor.getValue());
        } catch (SensorBrokenException e) {
            //e.printStackTrace();
        }
        try {
            model.addAttribute("temperature", temperatureSensor.getValue());
        } catch (SensorBrokenException e) {
            //e.printStackTrace();
        }
        model.addAttribute("state", maintenanceServer.getLastState());
        model.addAttribute("data", dataServer.getLastData());
        model.addAttribute("serverdata", dataServer.getReceivedData());
        model.addAttribute("serverstate", maintenanceServer.getReceivedData());
        return "home";
    }

    @RequestMapping("/incwind")
    public String incwind() {
        try {
            windSensor.setValue(windSensor.getValue() + 50);
        } catch (SensorBrokenException e) {
            windSensor.setValue(0);
        }
        return "redirect:/";
    }

    @RequestMapping("/decwind")
    public String decwind() {
        try {
            windSensor.setValue(windSensor.getValue() - 50);
        } catch (SensorBrokenException e) {
            windSensor.setValue(0);
        }
        return "redirect:/";
    }

    @RequestMapping("/inctemperature")
    public String inctemperature() {
        try {
            temperatureSensor.setValue(temperatureSensor.getValue() + 10);
        } catch (SensorBrokenException e) {
            temperatureSensor.setValue(19);
        }
        return "redirect:/";
    }

    @RequestMapping("/dectemperature")
    public String dectemperature() {
        try {
            temperatureSensor.setValue(temperatureSensor.getValue() - 10);
        } catch (SensorBrokenException e) {
            temperatureSensor.setValue(19);
        }
        return "redirect:/";
    }


    @RequestMapping("/incbattery")
    public String incbattery() {
        try {
            batteryLevel.setValue(batteryLevel.getValue() + 10);
        } catch (SensorBrokenException e) {
            batteryLevel.setValue(30);
        }
        return "redirect:/";
    }

    @RequestMapping("/decbattery")
    public String decbattery() {
        try {
            batteryLevel.setValue(batteryLevel.getValue() - 10);
        } catch (SensorBrokenException e) {
            batteryLevel.setValue(30);
        }
        return "redirect:/";
    }

    @RequestMapping("/getdata")
    public String getdata(){
        dataServer.setWaiting();
        return "redirect:/";
    }

    @RequestMapping("/getstate")
    public String getstate() {
        maintenanceServer.setWaiting();
        return "redirect:/";
    }

    @RequestMapping("/clear")
    public String clear(){
        dataServer.clearData();
        maintenanceServer.clearData();
        return "redirect:/";
    }

}