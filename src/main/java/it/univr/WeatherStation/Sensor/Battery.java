package it.univr.WeatherStation.Sensor;

public class Battery extends Sensor{

    public Battery(int value) {
        super(value);
    }

    @Override
    public void setValue(int value) {
        if(value < 0)
            super.setValue(0);
        else if(value > 100)
            super.setValue(100);
        else
            super.setValue(value);
    }
}
