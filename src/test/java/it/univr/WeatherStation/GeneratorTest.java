package it.univr.WeatherStation;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void isOpen() {
        Generator g = new Generator(true);
        assertTrue(g.isOpen());
    }

    @Test
    public void setOpen() {
        Generator g = new Generator(true);
        g.setOpen(false);
        assertFalse(g.isOpen());
    }
}