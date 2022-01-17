package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertTrue;

public class TestCheckBatteryRecover extends BaseTest{

    @Test
    public void TestCheckBatteryRecover(){
        HomePO homePage = new HomePO(driver);
        homePage.decbatteryButtonClick();
        homePage.decbatteryButtonClick();
        String receivedData = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);
        assertTrue(receivedData.contains("\"batteryLevel\":\"10%\""));
        assertTrue(receivedData.contains("\"energySaving\":\"true\""));
        homePage.incbatteryButtonClick();
        receivedData = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedData, "}") == 2);
        receivedData = receivedData.split("}")[1];
        assertTrue(receivedData.contains("\"batteryLevel\":\"20%\""));
        assertTrue(receivedData.contains("\"energySaving\":\"false\""));
    }
}

