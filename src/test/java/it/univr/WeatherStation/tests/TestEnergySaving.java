package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.PO.HomePO;
import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEnergySaving extends BaseTest {

    @Test
    public void TestEnergySaving(){
        HomePO homePage = new HomePO(driver);
        homePage.decbatteryButtonClick();
        homePage.decbatteryButtonClick();
        String receivedState = homePage.getTextareaMS();
        String receivedData = homePage.getTextareaDS();

        assertEquals("10%", homePage.getMockBatteryLevel());
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(receivedState.contains("\"batteryLevel\":\"10%\""));
        assertTrue(receivedData.equals(""));

        homePage.getdataButtonClick();
        homePage.getstateButtonClick();

        receivedState = homePage.getTextareaMS();
        receivedData = homePage.getTextareaDS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(receivedState.contains("\"batteryLevel\":\"10%\""));
        assertTrue(receivedData.equals(""));
    }
}
