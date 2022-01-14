package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;

public class TestIncreaseWindSpeed extends BaseTest{

    @Test
    public void TestIncreaseWindSpeed(){
        HomePO homePage = new HomePO(driver);
        homePage.incwindButtonClick();
        assertEquals("50 Km/h", homePage.getMockBatteryLevel());
    }
}
