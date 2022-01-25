package it.univr.WeatherStation.tests;


import it.univr.WeatherStation.PO.HomePO;
import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBreakWindAbove extends BaseTest {

    @Test
    public void TestBreakWindAbove(){
        HomePO homePage = new HomePO(driver);
        for(int i = 0; i < 3; i++)
            homePage.incwindButtonClick();
        homePage.getdataButtonClick();
        driver.navigate().refresh();
        String receivedData = homePage.getTextareaDS();
        String receivedState = homePage.getTextareaMS();
        assertEquals("Unknown", homePage.getMockWind());
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);
        assertTrue(StringUtils.countMatches(receivedState, "}") == 2);
        assertTrue(!receivedData.contains("\"wind\""));
        receivedState = homePage.getTextareaMS().split("}")[1];
        assertTrue(receivedState.contains("\"sensorsBroken\":[\"wind\"]"));

        //RESET
        homePage.decwindButtonClick();
        assertEquals("0 km/h", homePage.getMockWind());
    }
}
