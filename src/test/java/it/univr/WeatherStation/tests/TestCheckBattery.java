package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertTrue;

public class TestCheckBattery extends BaseTest{

    @Test
    public void TestCheckBattery(){
        HomePO homePage = new HomePO(driver);
        homePage.decbatteryButtonClick();
        homePage.decbatteryButtonClick();
        String receivedData = homePage.getTextareaMS();
        //System.out.println(receivedData);
        assertTrue(receivedData.contains("10%"));
        assertTrue(receivedData.contains("\"energySaving\":\"true\""));
        homePage.decbatteryButtonClick();
        receivedData = homePage.getTextareaMS();
        assertTrue(receivedData.contains("0%"));
        assertTrue(receivedData.contains("\"isCharging\":\"false\""));
    }
}

