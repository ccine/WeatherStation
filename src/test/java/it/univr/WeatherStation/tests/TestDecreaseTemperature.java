package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;

public class TestDecreaseTemperature extends BaseTest{

    @Test
    public void TestDecreaseTemperature(){
        HomePO homePage = new HomePO(driver);
        homePage.dectemperatureButtonClick();
        assertEquals("9 °C", homePage.getMockTemperature());

        //RESET
        homePage.inctemperatureButtonClick();
        assertEquals("19 °C", homePage.getMockTemperature());
    }
}
