package it.univr.WeatherStation;

import org.json.JSONObject;

public class Server {

    private String receivedData;
    private boolean isWaiting;

    public Server() {
        this.receivedData = "";
        this.isWaiting = false;
    }

    public void sendData(JSONObject data){
        receivedData += data.toString() + "\n";
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
