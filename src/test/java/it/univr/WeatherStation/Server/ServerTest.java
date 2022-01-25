package it.univr.WeatherStation.Server;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void sendData() {
        /*Server s = new Server();
        JSONObject j = new JSONObject();
        s.sendData(j);
        assertEquals(j, s.lastValue);
        assertFalse(s.getReceivedData().isEmpty());
        assertFalse(s.isWaiting());*/
    }

    @Test
    public void isWaiting() {
        /*Server s = new Server();
        assertFalse(s.isWaiting());
        s.setWaiting(true);
        assertTrue(s.isWaiting());*/
    }

    @Test
    public void getReceivedData() {
        Server s = new Server();
        JSONObject j = new JSONObject();
        s.sendData(j);
        assertEquals(j.toString()+"\n", s.getReceivedData());
    }

    @Test
    public void setWaiting() {
        // TODO
        /*Server s = new Server();
        assertFalse(s.isWaiting());
        s.setWaiting(true);
        assertTrue(s.isWaiting());*/
    }

    @Test
    public void clearData() {
        /*Server s = new Server();
        s.clearData();
        assertFalse(s.isWaiting());
        assertEquals("", s.getReceivedData());*/
    }
}