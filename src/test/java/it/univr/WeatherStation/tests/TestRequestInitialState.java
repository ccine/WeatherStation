package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRequestInitialState extends BaseTest{

    @Test
    public void TestRequestInitialState(){
        HomePO homePage = new HomePO(driver);
        homePage.getstateButtonClick();
        String receivedState = homePage.getTextareaMS();
        assertTrue(StringUtils.countMatches(receivedState, "}") == 1);

        assertTrue(receivedState.contains("\"station\":1234"));
        assertTrue(receivedState.contains("\"isCharging\":true"));
        assertTrue(receivedState.contains("\"energySaving\":false"));
        assertTrue(receivedState.contains("\"batteryLevel\":\"30%\""));
        Date data = new Date(System.currentTimeMillis());
        assertTrue(receivedState.contains(data.toString()));

        assertEquals(data, homePage.getStationStateTimestamp().split(" ")[0]);
        assertEquals("1234", homePage.getStationStateStationID());
        assertEquals("30%", homePage.getStationStateBatteryLevel());
        assertEquals("true", homePage.getStationStateCharging());
        assertEquals("false", homePage.getStationStateEnergySaving());
    }
}