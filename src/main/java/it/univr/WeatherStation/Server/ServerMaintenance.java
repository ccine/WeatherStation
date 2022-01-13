package it.univr.WeatherStation.Server;

import org.json.JSONException;

public class ServerMaintenance extends Server {

    public StationState getLastState(){
        try {
            return new StationState(
                    (lastValue.get("timestamp")).toString(),
                    Integer.toString((Integer) lastValue.get("station")),
                    (String) lastValue.get("batteryLevel"),
                    Boolean.toString((Boolean) lastValue.get("isCharging")),
                    Boolean.toString((Boolean) lastValue.get("energySaving"))
            );
        } catch (JSONException | NullPointerException e) {
            return new StationState();
        }
    }
}
