package it.univr.WeatherStation.Server;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ServerDataTest {

    @Test
    public void getLastData() throws JSONException {
        ServerData sd = new ServerData();
        StationData std = sd.getLastData();
        assertEquals("", std.getTimestamp());
        assertEquals("", std.getStation());
        assertEquals("", std.getWind());
        assertEquals("", std.getTemperature());
        assertEquals("", std.getLight());
        assertEquals("", std.getHumidity());

        JSONObject json = new JSONObject();

        sd.sendData(json);
        std = sd.getLastData();
        assertEquals("", std.getTimestamp());
        assertEquals("", std.getStation());
        assertEquals("", std.getWind());
        assertEquals("", std.getTemperature());
        assertEquals("", std.getLight());
        assertEquals("", std.getHumidity());



        Timestamp ts = new Timestamp(System.currentTimeMillis());
        json.put("timestamp", ts);
        json.put("station", 1234);
        json.put("wind", "10");
        json.put("temperature", "20");
        json.put("light", "30");
        json.put("humidity", "40");


        sd.sendData(json);
        std = sd.getLastData();
        assertEquals(ts.toString(), std.getTimestamp());
        assertEquals("1234", std.getStation());
        assertEquals("10", std.getWind());
        assertEquals("20", std.getTemperature());
        assertEquals("30", std.getLight());
        assertEquals("40", std.getHumidity());
    }
}