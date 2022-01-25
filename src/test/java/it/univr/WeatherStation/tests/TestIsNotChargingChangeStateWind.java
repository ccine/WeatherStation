package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestIsNotChargingChangeStateWind extends BaseTest{

    @Test
    public void TestIsNotChargingWind(){
        HomePO homePage = new HomePO(driver);
        homePage.incwindButtonClick();
        driver.navigate().refresh();
        String receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(receivedState.contains("\"isCharging\":false"));
        homePage.decwindButtonClick();
        receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 2);
        receivedState = receivedState.split("}")[1];
        assertTrue(receivedState.contains("\"isCharging\":true"));

        //RESET
        assertEquals("0 km/h", homePage.getMockWind());
    }
}