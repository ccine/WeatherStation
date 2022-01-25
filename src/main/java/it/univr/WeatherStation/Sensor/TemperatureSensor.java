package it.univr.WeatherStation.Sensor;

public class TemperatureSensor extends Sensor{

    public TemperatureSensor(int value) {
        super(value);
    }

    @Override
    public void setValue(int value){
        if (value < -20 || value > 40) {
            broken = true;
        } else {
            broken = false;
            super.setValue(value);
        }


    }
}
