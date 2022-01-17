package it.univr.WeatherStation.Server;

import org.junit.Test;

import static org.junit.Assert.*;

public class StationDataTest {

    @Test
    public void getTimestamp() {
        StationData sd = new StationData("2022-01-17", "1234", "wind", "temperature", "light", "humidity");
        assertEquals("2022-01-17", sd.getTimestamp());
        sd = new StationData();
        assertEquals("", sd.getTimestamp());
    }

    @Test
    public void getStation() {
        StationData sd = new StationData("2022-01-17", "1234", "wind", "temperature", "light", "humidity");
        assertEquals("1234", sd.getStation());
    }

    @Test
    public void getWind() {
        StationData sd = new StationData("2022-01-17", "1234", "wind", "temperature", "light", "humidity");
        assertEquals("wind", sd.getWind());
    }

    @Test
    public void getTemperature() {
        StationData sd = new StationData("2022-01-17", "1234", "wind", "temperature", "light", "humidity");
        assertEquals("temperature", sd.getTemperature());
    }

    @Test
    public void getLight() {
        StationData sd = new StationData("2022-01-17", "1234", "wind", "temperature", "light", "humidity");
        assertEquals("light", sd.getLight());
    }

    @Test
    public void getHumidity() {
        StationData sd = new StationData("2022-01-17", "1234", "wind", "temperature", "light", "humidity");
        assertEquals("humidity", sd.getHumidity());
    }
}