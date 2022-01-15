package it.univr.WeatherStation.utils;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        String path = "src/test/resources/chromedriver";
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", Paths.get(path + "_win32_96/chromedriver.exe").toString());
        } else if (SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chrome.driver", Paths.get(path + "_mac64_96/chromedriver").toString());
        } else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver", Paths.get(path + "_linux64_96/chromedriver").toString());
        }
        if(driver == null) {
            org.openqa.selenium.chrome.ChromeOptions chrome_options = new ChromeOptions();
            //chrome_options.addArguments("--headless");
            driver = new ChromeDriver(chrome_options);
            driver.manage().window().maximize();
            driver.get("http://localhost:8080");
        }
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}