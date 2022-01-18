package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertTrue;

public class TestCheckBatteryTurnOff extends BaseTest{

    @Test
    public void TestCheckBatteryTurnOff(){
        HomePO homePage = new HomePO(driver);
        homePage.decbatteryButtonClick();
        homePage.decbatteryButtonClick();
        String receivedData = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);
        assertTrue(receivedData.contains("10%"));
        assertTrue(receivedData.contains("\"energySaving\":\"true\""));
        homePage.decbatteryButtonClick();
        receivedData = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedData, "}") == 3);
        String receivedData1 = receivedData.split("}")[1];
        assertTrue(receivedData1.contains("0%"));
        assertTrue(receivedData1.contains("\"isCharging\":\"false\""));
        String receivedData2 = receivedData.split("}")[2];
        assertTrue(receivedData1.contains("\"error\":\"Station is stopping\""));
    }
}

