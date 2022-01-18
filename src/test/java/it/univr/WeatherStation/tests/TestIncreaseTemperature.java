package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;

public class TestIncreaseTemperature extends BaseTest{

    @Test
    public void TestIncreaseTemperature(){
        HomePO homePage = new HomePO(driver);
        homePage.inctemperatureButtonClick();
        assertEquals("29 °C", homePage.getMockTemperature());

        //RESET
        homePage.dectemperatureButtonClick();
        assertEquals("19 °C", homePage.getMockTemperature());
    }
}
