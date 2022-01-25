package it.univr.WeatherStation.tests;


import it.univr.WeatherStation.PO.HomePO;
import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBreakTemperatureUnder extends BaseTest {

    @Test
    public void TestBreakTemperatureUnder(){
        HomePO homePage = new HomePO(driver);
        for(int i = 0; i < 4; i++)
            homePage.dectemperatureButtonClick();
        homePage.getdataButtonClick();
        driver.navigate().refresh();
        String receivedData = homePage.getTextareaDS();
        String receivedState = homePage.getTextareaMS();
        assertEquals("Unknown", homePage.getMockTemperature());
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(!receivedData.contains("\"temperature\""));
        assertTrue(receivedState.contains("\"sensorsBroken\":[\"temperature\"]"));

        //RESET
        homePage.inctemperatureButtonClick();
        assertEquals("19 °C", homePage.getMockTemperature());
    }
}