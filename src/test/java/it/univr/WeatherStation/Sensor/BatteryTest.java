package it.univr.WeatherStation.Sensor;

import org.junit.Test;

import static org.junit.Assert.*;

public class BatteryTest {

    @Test
    public void setValue() {
        Battery b = new Battery(30);
        assertEquals(30, b.value);
        b.setValue(-1);
        assertEquals(0, b.value);
        b.setValue(101);
        assertEquals(100, b.value);
        b.setValue(65);
        assertEquals(65, b.value);
    }
}