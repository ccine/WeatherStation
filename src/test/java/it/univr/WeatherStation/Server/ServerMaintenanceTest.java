package it.univr.WeatherStation.Server;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ServerMaintenanceTest {

    @Test
    public void getLastState() throws JSONException {
        JSONObject json = new JSONObject();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        json.put("timestamp", ts);
        json.put("station", 1234);
        json.put("batteryLevel", "10");
        json.put("isCharging", true);
        json.put("energySaving", false);
        ServerMaintenance sm = new ServerMaintenance();
        sm.sendData(json);
        StationState ss = sm.getLastState();
        assertEquals(ts.toString(), ss.getTimestamp());
        assertEquals("1234", ss.getStation());
        assertEquals("10", ss.getBatteryLevel());
        assertEquals("true", ss.getIsCharging());
        assertEquals("false", ss.getEnergySaving());
    }
}