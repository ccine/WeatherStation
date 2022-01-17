package it.univr.WeatherStation.Sensor;

public class Sensor {
    protected int value;
    protected boolean broken = false;

    public Sensor(int value) {
        try {
            setValue(value);
        } catch (SensorBrokenException e) {
            this.value = 0;
        }
    }

    public int getValue() throws SensorBrokenException {
        if (broken) throw new SensorBrokenException();
        return value;
    }

    public void setValue(int value) throws SensorBrokenException { this.value = value; };
}
