package it.univr.WeatherStation.Sensor;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class SensorErrorsTest {

    @Test
    public void popErrors() {
        SensorErrors se = new SensorErrors();
        assertEquals(0, se.popErrors().length());
    }

    @Test
    public void pushErrors() {
        SensorErrors se = new SensorErrors();
        JSONObject json = new JSONObject();
        se.pushErrors(json);
        assertEquals(json, se.popErrors());
    }
}