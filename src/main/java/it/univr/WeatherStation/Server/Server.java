package it.univr.WeatherStation.Server;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

public class Server {

    private String receivedData;
    private boolean isWaiting;
    protected JSONObject lastValue;

    public Server() {
        this.receivedData = "";
        this.isWaiting = false;
    }

    public void sendData(JSONObject data){
        receivedData += data.toString() + "\n";
        lastValue = data;
        isWaiting = false;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public String getReceivedData() {
        return receivedData;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

}