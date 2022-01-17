package it.univr.WeatherStation.PO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePO extends PageObject{

    public HomePO(WebDriver driver){
        super(driver);
    }

    //STATION DATA
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]/label[1]")
    private WebElement StationDataTimestamp;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[2]/label[1]")
    private WebElement StationDataStationID;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[3]/label[1]")
    private WebElement StationDataWind;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[4]/label[1]")
    private WebElement StationDataTemperature;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[5]/label[1]")
    private WebElement StationDataLight;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[6]/label[1]")
    private WebElement StationDataHumidity;


    //STATION STATE
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/label[1]")
    private WebElement StationStateTimestamp;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[2]/label[1]")
    private WebElement StationStateStationID;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[3]/label[1]")
    private WebElement StationStateBatteryLevel;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[4]/label[1]")
    private WebElement StationStateCharging;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[5]/label[1]")
    private WebElement StationStateEnergySaving;


    //MOCK VALUES
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[1]/label[1]")
    private WebElement MockBatteryLevel;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[2]/label[1]")
    private WebElement MockWind;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[3]/label[1]")
    private WebElement MockTemperature;


    //BOTTONI
    @FindBy(id = "incwind")
    private WebElement incwindButton;

    @FindBy(id = "decwind")
    private WebElement decwindButton;

    @FindBy(id = "inctemperature")
    private WebElement inctemperatureButton;

    @FindBy(id = "dectemperature")
    private WebElement dectemperatureButton;

    @FindBy(id = "incbattery")
    private WebElement incbatteryButton;

    @FindBy(id = "decbattery")
    private WebElement decbatteryButton;

    @FindBy(id = "getdata")
    private WebElement getdataButton;

    @FindBy(id = "getstate")
    private WebElement getstateButton;


    //OUTPUT
    @FindBy(id = "textareaDS")
    private WebElement textareaDS;

    @FindBy(id = "textareaMS")
    private WebElement textareaMS;


    //METODI
    public void javaScriptClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }


    //METODI TEST
    public String getStationDataTimestamp(){
        return StationDataTimestamp.getText();
    }

    public String getStationDataStationID() {
        return StationDataStationID.getText();
    }

    public String getStationDataWind() {
        return StationDataWind.getText();
    }

    public String getStationDataTemperature() {
        return StationDataTemperature.getText();
    }

    public String getStationDataLight() {
        return StationDataLight.getText();
    }

    public String getStationDataHumidity() {
        return StationDataHumidity.getText();
    }

    public String getStationStateTimestamp() {
        return StationStateTimestamp.getText();
    }

    public String getStationStateStationID() {
        return StationStateStationID.getText();
    }

    public String getStationStateBatteryLevel() {
        return StationStateBatteryLevel.getText();
    }

    public String getStationStateCharging() {
        return StationStateCharging.getText();
    }

    public String getStationStateEnergySaving() {
        return StationStateEnergySaving.getText();
    }

    public String getMockBatteryLevel() {
        return MockBatteryLevel.getText();
    }

    public String getMockWind() {
        return MockWind.getText();
    }

    public String getMockTemperature() {
        return MockTemperature.getText();
    }

    public String getTextareaDS() {
        return textareaDS.getText();
    }

    public String getTextareaMS() {
        return textareaMS.getAttribute("value");
    }

    public void incwindButtonClick(){
        javaScriptClick(incwindButton);
    }

    public void decwindButtonClick(){
        javaScriptClick(decwindButton);
    }

    public void inctemperatureButtonClick(){
        javaScriptClick(inctemperatureButton);
    }

    public void dectemperatureButtonClick(){
        javaScriptClick(dectemperatureButton);
    }

    public void incbatteryButtonClick(){
        javaScriptClick(incbatteryButton);
    }

    public void decbatteryButtonClick(){
        javaScriptClick(decbatteryButton);
    }

    public void getdataButtonClick(){
        javaScriptClick(getdataButton);
    }

    public void getstateButtonClick(){
        javaScriptClick(getstateButton);
    }
}
