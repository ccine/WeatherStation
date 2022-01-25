package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertEquals;

public class TestDecreaseBatteryLevel extends BaseTest{

    @Test
    public void TestDecreaseBatteryLevel(){
        HomePO homePage = new HomePO(driver);
        homePage.decbatteryButtonClick();
        assertEquals("20%", homePage.getMockBatteryLevel());

        //RESET
        homePage.incbatteryButtonClick();
        assertEquals("30%", homePage.getMockBatteryLevel());
    }
}
