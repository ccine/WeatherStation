package it.univr.WeatherStation;

public class Battery {
    private int level;

    public Battery(int level){ this.level = level; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level < 0)
            this.level = 0;
        else if(level > 100)
            this.level = 100;
        else
            this.level = level;
    }
}
