package it.univr.WeatherStation.Server;

import org.json.JSONException;

public class ServerData extends Server {

    public StationData getLastData() {
        try {
            return new StationData(
                    (lastValue.get("timestamp")).toString(),
                    Integer.toString((Integer) lastValue.get("station")),
                    (String) lastValue.get("wind"),
                    (String) lastValue.get("temperature"),
                    (String) lastValue.get("light"),
                    (String) lastValue.get("humidity")
            );
        } catch (JSONException | NullPointerException e) {
            return new StationData();
        }
    }

}
