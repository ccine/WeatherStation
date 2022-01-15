package it.univr.WeatherStation.tests;

import it.univr.WeatherStation.utils.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class CheckTestCase extends BaseTest {

    @Test
    public void checkEverythingWorks() {
        String expectedValue = "Weather Station Monitor";
        String currentValue = driver.findElement(By.xpath("//h1[contains(text(),'Weather Station Monitor')]")).getText();
        assertEquals(expectedValue, currentValue);
    }


}
