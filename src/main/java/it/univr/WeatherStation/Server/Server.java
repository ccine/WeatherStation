package it.univr.WeatherStation.Server;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

public class Server {

    private String receivedData;
    protected JSONObject lastValue;

    public Server() {
        receivedData = "";
        lastValue = new JSONObject();
    }

    public void sendData(JSONObject data){
        receivedData += data.toString() + "\n";
        lastValue = data;
    }

    public void waitServer() throws InterruptedException {
        synchronized (this){
            wait();
        }
    }

    public String getReceivedData() {
        return receivedData;
    }

    public void setWaiting() {
        synchronized (this){
            notify();
        }
    }

    public void clearData(){
        receivedData = "";
        lastValue = new JSONObject();
    }

}
