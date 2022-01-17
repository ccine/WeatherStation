package it.univr.WeatherStation.Server;

import org.junit.Test;

import static org.junit.Assert.*;

public class StationStateTest {

    @Test
    public void getTimestamp() {
        StationState ss = new StationState("2022-01-17", "1234", "battery", "isCharging", "energySaving");
        assertEquals("2022-01-17", ss.getTimestamp());
        ss = new StationState();
        assertEquals("", ss.getTimestamp());
    }

    @Test
    public void getStation() {
        StationState ss = new StationState("2022-01-17", "1234", "battery", "isCharging", "energySaving");
        assertEquals("1234", ss.getStation());
    }

    @Test
    public void getBatteryLevel() {
        StationState ss = new StationState("2022-01-17", "1234", "battery", "isCharging", "energySaving");
        assertEquals("battery", ss.getBatteryLevel());
    }

    @Test
    public void getIsCharging() {
        StationState ss = new StationState("2022-01-17", "1234", "battery", "isCharging", "energySaving");
        assertEquals("isCharging", ss.getIsCharging());
    }

    @Test
    public void getEnergySaving() {
        StationState ss = new StationState("2022-01-17", "1234", "battery", "isCharging", "energySaving");
        assertEquals("energySaving", ss.getEnergySaving());
    }
}