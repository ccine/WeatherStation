package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestIncreaseWindSpeed extends BaseTest{

    @Test
    public void TestIncreaseWindSpeed(){
        HomePO homePage = new HomePO(driver);
        homePage.incwindButtonClick();
        assertEquals("50 km/h", homePage.getMockWind());

        //RESET
        homePage.decwindButtonClick();
        assertEquals("0 km/h", homePage.getMockWind());
    }
}
