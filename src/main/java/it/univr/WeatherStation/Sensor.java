package it.univr.WeatherStation;

public class Sensor {
    protected int value;

    public Sensor(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){ this.value = value; };
}
