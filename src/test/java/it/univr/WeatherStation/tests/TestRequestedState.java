package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.*;

public class TestRequestedState extends BaseTest{

    @Test
    public void TestRequestedState(){
        HomePO homePage = new HomePO(driver);
        homePage.getstateButtonClick();
        assertTrue(StringUtils.countMatches(homePage.getTextareaMS(), "}") == 1);
    }
}