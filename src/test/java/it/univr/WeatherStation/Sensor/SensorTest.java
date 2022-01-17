package it.univr.WeatherStation.Sensor;

import org.junit.Test;

import static org.junit.Assert.*;

public class SensorTest {

    @Test
    public void getValue() throws SensorBrokenException {
        Sensor s = new Sensor(3);
        assertEquals(3, s.getValue());
    }

    @Test
    public void setValue() throws SensorBrokenException {
        Sensor s = new Sensor(3);
        s.setValue(40);
        assertEquals(40, s.getValue());
    }
}