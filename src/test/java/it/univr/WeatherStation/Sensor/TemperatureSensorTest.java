package it.univr.WeatherStation.Sensor;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureSensorTest {

    @Test
    public void setValue() {
        TemperatureSensor ts = new TemperatureSensor(20);
        ts.setValue(-30);
        assertThrows(SensorBrokenException.class, () -> ts.getValue());
    }
}