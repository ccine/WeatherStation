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
        assertEquals("Unknown", homePage.getMockWind());
        assertTrue(StringUtils.countMatches(homePage.getTextareaMS(), "}") == 1);
        assertTrue(homePage.getTextareaMS().contains("\"sensorBroken\":[\"wind\"]"));
    }
}