package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCheckBatteryRecover extends BaseTest{

    @Test
    public void TestCheckBatteryRecover(){
        HomePO homePage = new HomePO(driver);
        homePage.decbatteryButtonClick();
        homePage.decbatteryButtonClick();
        String receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(receivedState.contains("\"batteryLevel\":\"10%\""));
        assertTrue(receivedState.contains("\"energySaving\":\"true\""));
        homePage.incbatteryButtonClick();
        receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 2);
        receivedState = receivedState.split("}")[1];
        assertTrue(receivedState.contains("\"batteryLevel\":\"20%\""));
        assertTrue(receivedState.contains("\"energySaving\":\"false\""));

        //RESET
        homePage.incbatteryButtonClick();
        assertEquals("30%", homePage.getMockBatteryLevel());
    }
}

