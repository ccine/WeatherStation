package it.univr.WeatherStation;

public class WindSensor extends Sensor{

    public WindSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) {
        if (value < 0)
            this.value = 0;
        else if (value > 100)
            this.value = 100;
        else
            this.value = value;
    }
}
