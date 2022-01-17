package it.univr.WeatherStation;

import it.univr.WeatherStation.Sensor.Battery;
import it.univr.WeatherStation.Sensor.Sensor;
import it.univr.WeatherStation.Sensor.TemperatureSensor;
import it.univr.WeatherStation.Sensor.WindSensor;
import it.univr.WeatherStation.Server.ServerData;
import it.univr.WeatherStation.Server.ServerMaintenance;
import org.junit.Test;

import static org.junit.Assert.*;

public class StationTest {

    @Test
    public void run() throws InterruptedException {
        WindSensor windSensor = new WindSensor(0);
        TemperatureSensor temperatureSensor = new TemperatureSensor(19);
        Battery batteryLevel = new Battery(30);
        ServerData dataServer = new ServerData();
        ServerMaintenance maintenanceServer = new ServerMaintenance();
        Station weatherStation = new Station(1234, windSensor, temperatureSensor, new Sensor(50), new Sensor(50000), batteryLevel, dataServer, maintenanceServer);
        dataServer.setWaiting(true);
        maintenanceServer.setWaiting(true);
        assertTrue(weatherStation.isRunning());
        batteryLevel.setValue(1);
        Thread.sleep(1000);
        assertFalse(weatherStation.isRunning());
    }

    @Test
    public void isRunning() {
        Station weatherStation = new Station(1234, new WindSensor(0), new TemperatureSensor(19), new Sensor(50), new Sensor(50000), new Battery(30), new ServerData(), new ServerMaintenance());
        assertTrue(weatherStation.isRunning());
    }
}