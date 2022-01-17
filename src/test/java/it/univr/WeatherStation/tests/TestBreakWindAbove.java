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
        assertEquals("Unknown", homePage.getMockWind());
        assertTrue(StringUtils.countMatches(homePage.getTextareaMS(), "}") == 1);
        assertTrue(homePage.getTextareaMS().contains("\"sensorBroken\":[\"wind\"]"));
    }
}
