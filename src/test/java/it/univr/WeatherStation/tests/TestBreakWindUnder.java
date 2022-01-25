package it.univr.WeatherStation.tests;


import it.univr.WeatherStation.PO.HomePO;
import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBreakWindUnder extends BaseTest {

    @Test
    public void TestBreakWindUnder(){
        HomePO homePage = new HomePO(driver);
        homePage.decwindButtonClick();
        homePage.getdataButtonClick();
        driver.navigate().refresh();
        String receivedData = homePage.getTextareaDS();
        String receivedState = homePage.getTextareaMS();
        assertEquals("Unknown", homePage.getMockWind());
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(!receivedData.contains("\"wind\""));
        assertTrue(receivedState.contains("\"sensorsBroken\":[\"wind\"]"));

        //RESET
        homePage.incwindButtonClick();
        assertEquals("0 km/h", homePage.getMockWind());
    }
}