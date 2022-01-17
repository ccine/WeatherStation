package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import it.univr.WeatherStation.PO.HomePO;

import static org.junit.Assert.assertTrue;

public class TestPeriodicMessage extends BaseTest{

    @Test
    public void TestPeriodicMessage() throws InterruptedException {
        HomePO homePage = new HomePO(driver);
        Thread.sleep(60000);
        driver.navigate().refresh();
        String receivedData = homePage.getTextareaDS();
        assertTrue(receivedData.contains("\"manualRequest\":false"));
    }
}
