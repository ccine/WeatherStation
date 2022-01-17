package it.univr.WeatherStation.Server;

import org.json.JSONException;

import java.sql.Timestamp;

public class ServerMaintenance extends Server {

    public StationState getLastState(){
        try {
            String timestamp;
            String station;
            String batteryLevel;
            String isCharging;
            String energySaving;
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
                batteryLevel = (String) lastValue.get("batteryLevel");
            } catch (JSONException jex) {
                batteryLevel = "";
            }
            try {
                isCharging = Boolean.toString((Boolean) lastValue.get("isCharging"));
            } catch (JSONException jex) {
                isCharging = "";
            }
            try {
                energySaving = Boolean.toString((Boolean) lastValue.get("energySaving"));
            } catch (JSONException jex) {
                energySaving = "";
            }
            return new StationState(timestamp, station, batteryLevel, isCharging, energySaving);
        } catch (NullPointerException e) {
            return new StationState();
        }
    }
}
