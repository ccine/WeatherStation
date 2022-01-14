package it.univr.WeatherStation.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePO extends PageObject{

    public HomePO(WebDriver driver){
        super(driver);
    }

    //STATION DATA
    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]")
    private WebElement StationDataTimestamp;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[2]")
    private WebElement StationDataStationID;

    @FindBy(xpath = "//p[contains(text(),'Wind:')]")
    private WebElement StationDataWind;

    @FindBy(xpath = "//p[contains(text(),'Temperature:')]")
    private WebElement StationDataTemperature;

    @FindBy(xpath = "//p[contains(text(),'Light:')]")
    private WebElement StationDataLight;

    @FindBy(xpath = "//p[contains(text(),'Humidity:')]")
    private WebElement StationDataHumidity;


    //STATION STATE
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/p[1]")
    private WebElement StationStateTimestamp;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[2]")
    private WebElement StationStateStationID;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[3]")
    private WebElement StationStateBatteryLevel;

    @FindBy(xpath = "//p[contains(text(),'Charging:')]")
    private WebElement StationStateCharging;

    @FindBy(xpath = "//p[contains(text(),'EnergySaving:')]")
    private WebElement StationStateEnergySaving;


    //MOCK VALUES
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[1]")
    private WebElement MockBatteryLevel;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[2]")
    private WebElement MockWind;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[3]")
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
    public String getStationDataTimestamp(){
        return StationDataTimestamp.getText().substring(StationDataTimestamp.getText().indexOf(" "));
    }

    public String getStationDataStationID() {
        return StationDataStationID.getText().substring(StationDataStationID.getText().indexOf(" "));
    }

    public String getStationDataWind() {
        return StationDataWind.getText().substring(StationDataWind.getText().indexOf(" "));
    }

    public String getStationDataTemperature() {
        return StationDataTemperature.getText().substring(StationDataTemperature.getText().indexOf(" "));
    }

    public String getStationDataLight() {
        return StationDataLight.getText().substring(StationDataLight.getText().indexOf(" "));
    }

    public String getStationDataHumidity() {
        return StationDataHumidity.getText().substring(StationDataHumidity.getText().indexOf(" "));
    }

    public String getStationStateTimestamp() {
        return StationStateTimestamp.getText().substring(StationStateTimestamp.getText().indexOf(" "));
    }

    public String getStationStateStationID() {
        return StationStateStationID.getText().substring(StationStateStationID.getText().indexOf(" "));
    }

    public String getStationStateBatteryLevel() {
        return StationStateBatteryLevel.getText().substring(StationStateBatteryLevel.getText().indexOf(" "));
    }

    public String getStationStateCharging() {
        return StationStateCharging.getText().substring(StationStateCharging.getText().indexOf(" "));
    }

    public String getStationStateEnergySaving() {
        return StationStateEnergySaving.getText().substring(StationStateEnergySaving.getText().indexOf(" "));
    }

    public String getMockBatteryLevel() {
        return MockBatteryLevel.getText().substring(MockBatteryLevel.getText().indexOf(" "));
    }

    public String getMockWind() {
        return MockWind.getText().substring(MockWind.getText().indexOf(" "));
    }

    public String getMockTemperature() {
        return MockTemperature.getText().substring(MockTemperature.getText().indexOf(" "));
    }

    public String getTextareaDS() {
        return textareaDS.getText();
    }

    public String getTextareaMS() {
        return textareaMS.getText();
    }

    public void incwindButtonClick(){
        incwindButton.click();
    }

    public void decwindButtonClick(){
        decwindButton.click();
    }

    public void inctemperatureButtonClick(){
        inctemperatureButton.click();
    }

    public void dectemperatureButtonClick(){
        dectemperatureButton.click();
    }

    public void incbatteryButtonClick(){
        incbatteryButton.click();
    }

    public void decbatteryButtonClick(){
        decbatteryButton.click();
    }

    public void getdataButtonClick(){
        getdataButton.click();
    }

    public void getstateButtonClick(){
        getstateButton.click();
    }


}
