package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class TestRequestInitialState extends BaseTest{

    @Test
    public void TestRequestInitialState(){
        HomePO homePage = new HomePO(driver);
        homePage.getstateButtonClick();
        assertEquals(new Date(System.currentTimeMillis()), homePage.getStationStateTimestamp().split(" ")[0]);
        assertEquals("1234", homePage.getStationStateStationID());
        assertEquals("30%", homePage.getStationStateBatteryLevel());
        assertEquals("true", homePage.getStationStateCharging());
        assertEquals("false", homePage.getStationStateEnergySaving());
    }
}