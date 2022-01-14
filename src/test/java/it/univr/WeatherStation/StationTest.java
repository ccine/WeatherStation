package it.univr.WeatherStation;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;

class StationTest extends BaseTest{
    @Test
    public void TestIncreaseWindSpeed(){
        HomePO homePage = new HomePO(driver);
        homePage.incwindButtonClick();
        assertEquals("50 Km/h", homePage.getMockBatteryLevel());
    }

    @org.junit.jupiter.api.Test
    void run() {
    }
}