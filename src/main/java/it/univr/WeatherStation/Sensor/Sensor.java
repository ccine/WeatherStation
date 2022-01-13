package it.univr.WeatherStation.Sensor;

public class Sensor {
    protected int value;
    protected boolean broken = false;

    public Sensor(int value) {
        setValue(value);
    }

    public int getValue() throws SensorBrokenException {
        if (broken) throw new SensorBrokenException();
        return value;
    }

    public void setValue(int value){ this.value = value; };
}
