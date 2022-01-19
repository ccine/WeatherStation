package it.univr.WeatherStation.Sensor;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindSensorTest {

    @Test
    public void setValue() {
        WindSensor ws = new WindSensor(20);
        ws.setValue(-30);
        assertThrows(SensorBrokenException.class, () -> ws.getValue());
    }
}