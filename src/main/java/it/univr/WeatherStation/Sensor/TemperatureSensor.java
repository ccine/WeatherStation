package it.univr.WeatherStation.Sensor;

public class TemperatureSensor extends Sensor{

    public TemperatureSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) throws SensorBrokenException {
        if (!broken)
            this.value = value;
        if (value < -20 || value > 40) {
            broken = true;
            throw new SensorBrokenException();
        } else
            broken = false;
    }
}
