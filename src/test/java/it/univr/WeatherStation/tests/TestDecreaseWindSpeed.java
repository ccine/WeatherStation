package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;


public class TestDecreaseWindSpeed extends BaseTest{

    @Test
    public void TestDecreaseWindSpeed(){
        HomePO homePage = new HomePO(driver);
        homePage.decwindButtonClick();
        assertEquals("Unknown", homePage.getMockWind());
    }
}
