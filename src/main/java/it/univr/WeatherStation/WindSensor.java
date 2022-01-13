package it.univr.WeatherStation;

public class WindSensor extends Sensor{

    public WindSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) {
        if (value < 0 || value > 100)
            broken = true;
        this.value = value;
    }
}
