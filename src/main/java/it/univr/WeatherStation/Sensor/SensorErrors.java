package it.univr.WeatherStation.Sensor;

import org.json.JSONObject;

public class SensorErrors {
    private JSONObject errors;

    public SensorErrors() {
        this.errors = new JSONObject();
    }

    public JSONObject popErrors() {
        JSONObject ret = errors;
        errors = new JSONObject();
        return ret;
    }

    public void pushErrors(JSONObject errors) {
        synchronized (this){
            this.errors = errors;
            this.notify();
        }
    }
}
