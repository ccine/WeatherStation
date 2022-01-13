package it.univr.WeatherStation;

public class TemperatureSensor extends Sensor{

    public TemperatureSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) {
        if (value < -20 || value > 40)
            broken = true;
        this.value = value;
    }
}
