package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestIsNotChargingWind extends BaseTest{

    @Test
    public void TestIsNotChargingWind(){
        HomePO homePage = new HomePO(driver);
        homePage.incwindButtonClick();
        String receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(receivedState.contains("\"isCharging\":false"));

        //RESET
        homePage.decwindButtonClick();
        homePage.clearTextAreas();
        assertEquals("0 km/h", homePage.getMockWind());
        assertEquals("", homePage.getTextareaDS());
        assertEquals("", homePage.getTextareaMS());
    }
}