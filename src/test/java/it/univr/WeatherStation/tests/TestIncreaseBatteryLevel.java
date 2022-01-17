package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;

public class TestIncreaseBatteryLevel extends BaseTest{

    @Test
    public void TestIncreaseBatteryLevel(){
        HomePO homePage = new HomePO(driver);
        homePage.incbatteryButtonClick();
        assertEquals("40%", homePage.getMockBatteryLevel());

        //RESET
        homePage.decbatteryButtonClick();
        homePage.clearTextAreas();
        assertEquals("30%", homePage.getMockBatteryLevel());
        assertEquals("", homePage.getTextareaDS());
        assertEquals("", homePage.getTextareaMS());
    }
}
