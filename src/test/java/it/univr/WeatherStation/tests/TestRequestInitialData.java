package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRequestInitialData extends BaseTest{

    @Test
    public void TestRequestInitialData(){
        HomePO homePage = new HomePO(driver);
        homePage.getdataButtonClick();
        String receivedData = homePage.getTextareaDS();
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);

        assertTrue(receivedData.contains("\"light\":\"50000 lm\""));
        assertTrue(receivedData.contains("\"station\":1234"));
        assertTrue(receivedData.contains("\"temperature\":\"19 °C\""));
        assertTrue(receivedData.contains("\"humidity\":\"50%\""));
        assertTrue(receivedData.contains("\"manualRequest\":true"));
        assertTrue(receivedData.contains("\"wind\":\"0 km/h\""));
        Date date = new Date(System.currentTimeMillis());
        assertTrue(receivedData.contains(date.toString()));

        assertEquals(date.toString(), homePage.getStationDataTimestamp().split(" ")[0]);
        assertEquals("1234", homePage.getStationDataStationID());
        assertEquals("0 km/h", homePage.getStationDataWind());
        assertEquals("19 °C", homePage.getStationDataTemperature());
        assertEquals("50000 lm", homePage.getStationDataLight());
        assertEquals("50%", homePage.getStationDataHumidity());
    }
}
