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
        assertEquals("Unknown", homePage.getMockWind());
        assertTrue(StringUtils.countMatches(homePage.getTextareaDS(), "}") == 1);
        assertTrue(StringUtils.countMatches(homePage.getTextareaMS(), "}") == 2);
        String receivedState = homePage.getTextareaMS().split("}")[1];
        assertTrue(receivedState.contains("\"sensorBroken\":[\"wind\"]"));

        //RESET
        for(int i = 0; i < 3; i++)
            homePage.decwindButtonClick();
        homePage.clearTextAreas();
        assertEquals("0 km/h", homePage.getMockWind());
        assertEquals("", homePage.getTextareaDS());
        assertEquals("", homePage.getTextareaMS());
        //clear server
    }
}