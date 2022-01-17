package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class TestRequestInitialData extends BaseTest{

    @Test
    public void TestRequestInitialData() throws InterruptedException {
        HomePO homePage = new HomePO(driver);
        homePage.getdataButtonClick();
        long start = System.currentTimeMillis();
        long end = start + 60*1000;
        while (System.currentTimeMillis() < end && homePage.getStationDataStationID().equals("")) {
            driver.navigate().refresh();
        }
        assertEquals(new Date(System.currentTimeMillis()), homePage.getStationDataTimestamp().split(" ")[0]);
        assertEquals("1234", homePage.getStationDataStationID());
        assertEquals("0 Km/h", homePage.getStationDataWind());
        assertEquals("19 °C", homePage.getStationDataTemperature());
        assertEquals("50000 lm", homePage.getStationDataLight());
        assertEquals("50%", homePage.getStationDataHumidity());
    }
}
