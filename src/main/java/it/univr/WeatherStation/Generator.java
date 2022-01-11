package it.univr.WeatherStation;

public class Generator {
    private boolean isOpen;

    public Generator(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
