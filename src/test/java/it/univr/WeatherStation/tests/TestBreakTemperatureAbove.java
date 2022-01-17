package it.univr.WeatherStation.tests;


import it.univr.WeatherStation.PO.HomePO;
import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import org.apache.commons.lang3.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBreakTemperatureAbove extends BaseTest {

    @Test
    public void TestBreakTemperatureAbove(){
        HomePO homePage = new HomePO(driver);
        for(int i = 0; i < 3; i++)
            homePage.inctemperatureButtonClick();
        assertEquals("Unknown", homePage.getMockTemperature());
        assertTrue(StringUtils.countMatches(homePage.getTextareaMS(), "}") == 1);
        assertTrue(homePage.getTextareaMS().contains("\"sensorBroken\":[\"temperature\"]"));
    }
}