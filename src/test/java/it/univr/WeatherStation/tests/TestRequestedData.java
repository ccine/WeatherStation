package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestRequestedData extends BaseTest{

    @Test
    public void TestRequestedData(){
        HomePO homePage = new HomePO(driver);
        homePage.getstateButtonClick();
        String receivedData = homePage.getTextareaDS();
        assertTrue(StringUtils.countMatches(receivedData, "}") == 1);
        assertTrue(receivedData.contains("\"manualRequest\":true"));
    }
}
