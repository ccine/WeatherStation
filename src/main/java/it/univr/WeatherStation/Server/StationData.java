package it.univr.WeatherStation.Server;

public class StationData {

    private String timestamp;
    private String station;
    private String wind;
    private String temperature;
    private String light;
    private String humidity;

    public StationData(String timestamp, String station, String wind, String temperature, String light, String humidity) {
        this.timestamp = timestamp;
        this.station = station;
        this.wind = wind;
        this.temperature = temperature;
        this.light = light;
        this.humidity = humidity;
    }

    public StationData() {
        timestamp = "";
        station = "";
        wind = "";
        temperature = "";
        light = "";
        humidity = "";
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStation() {
        return station;
    }

    public String getWind() {
        return wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLight() {
        return light;
    }

    public String getHumidity() {
        return humidity;
    }
}
