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
    public void isRunning() throws InterruptedException {
        WindSensor windSensor = new WindSensor(0);
        TemperatureSensor temperatureSensor = new TemperatureSensor(19);
        Battery batteryLevel = new Battery(30);
        ServerData dataServer = new ServerData();
        ServerMaintenance maintenanceServer = new ServerMaintenance();
        Station weatherStation = new Station(1234, windSensor, temperatureSensor, new Sensor(50), new Sensor(50000), batteryLevel, dataServer, maintenanceServer);
        assertTrue(weatherStation.isRunning());

        // Invio i dati al server
        dataServer.setWaiting();
        maintenanceServer.setWaiting();

        // Invio dati con sensori rotti
        windSensor.setValue(-1);
        temperatureSensor.setValue(-50);
        Thread.sleep(10);
        dataServer.setWaiting();

        // Extreme weather condition
        windSensor.setValue(50);
        Thread.sleep(10);
        windSensor.setValue(0);


        // Batteria sotto livello critico
        batteryLevel.setValue(19);
        Thread.sleep(10);
        batteryLevel.setValue(1);
        Thread.sleep(10); // Senza questa attesa la stazione meteo risulta nacora accesa
        assertFalse(weatherStation.isRunning());
    }
}