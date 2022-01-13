package it.univr.WeatherStation;

public class TemperatureSensor extends Sensor{

    public TemperatureSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) {
        if (value < -20)
            this.value = -20;
        else if(value > 40)
            this.value = 40;
        else
            this.value = value;
    }
}
