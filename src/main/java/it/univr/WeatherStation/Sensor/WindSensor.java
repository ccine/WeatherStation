package it.univr.WeatherStation.Sensor;

public class WindSensor extends Sensor{

    public WindSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) {
        if (!broken)
            this.value = value;
        if (value < 0 || value > 100)
            broken = true;
        else
            broken = false;
    }
}
