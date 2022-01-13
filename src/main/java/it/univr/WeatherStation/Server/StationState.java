package it.univr.WeatherStation.Server;

import java.sql.Timestamp;

public class StationState {

    private String timestamp;
    private String station;
    private String batteryLevel;
    private String isCharging;
    private String energySaving;

    public StationState(String timestamp, String station, String batteryLevel, String isCharging, String energySaving) {
        this.timestamp = timestamp;
        this.station = station;
        this.batteryLevel = batteryLevel;
        this.isCharging = isCharging;
        this.energySaving = energySaving;
    }

    public StationState() {
        timestamp = "";
        station = "";
        batteryLevel = "";
        isCharging = "";
        energySaving = "";
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStation() {
        return station;
    }

    public String getBatteryLevel() {
        return batteryLevel;
    }

    public String getIsCharging() {
        return isCharging;
    }

    public String getEnergySaving() {
        return energySaving;
    }
}
