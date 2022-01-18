package it.univr.WeatherStation.Server;

import org.json.JSONException;

import java.sql.Timestamp;

public class ServerData extends Server {

    public StationData getLastData() {
        try {
            String timestamp;
            String station;
            String wind;
            String temperature;
            String light;
            String humidity;
            try {
                timestamp = ((Timestamp) lastValue.get("timestamp")).toString();
            } catch (JSONException jex) {
                timestamp = "";
            }
            try {
                station = Integer.toString((Integer) lastValue.get("station"));
            } catch (JSONException jex) {
                station = "";
            }
            try {
                wind = (String) lastValue.get("wind");
            } catch (JSONException jex) {
                wind = "";
            }
            try {
                temperature = (String) lastValue.get("temperature");
            } catch (JSONException jex) {
                temperature = "";
            }
            try {
                light = (String) lastValue.get("light");
            } catch (JSONException jex) {
                light = "";
            }
            try {
                humidity = (String) lastValue.get("humidity");
            } catch (JSONException jex) {
                humidity = "";
            }
            return new StationData(timestamp, station, wind, temperature, light, humidity);
        } catch (NullPointerException e) {
            return new StationData();
        }
    }

}
