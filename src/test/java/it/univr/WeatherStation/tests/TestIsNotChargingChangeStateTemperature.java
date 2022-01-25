package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestIsNotChargingChangeStateTemperature extends BaseTest{

    @Test
    public void TestIsNotChargingTemperature(){
        HomePO homePage = new HomePO(driver);
        for(int i = 0; i < 3; i++)
            homePage.dectemperatureButtonClick();
        String receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);
        assertTrue(receivedState.contains("\"isCharging\":false"));
        homePage.inctemperatureButtonClick();
        receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 2);
        receivedState = receivedState.split("}")[1];
        assertTrue(receivedState.contains("\"isCharging\":true"));

        //RESET
        for(int i = 0; i < 2; i++)
            homePage.inctemperatureButtonClick();
        assertEquals("19 °C", homePage.getMockTemperature());
    }
}