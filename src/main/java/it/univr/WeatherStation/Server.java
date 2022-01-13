package it.univr.WeatherStation;

public class Server {

    private String receivedData;
    private boolean isWaiting;

    public Server() {
        this.receivedData = "";
        this.isWaiting = false;
    }

    public void sendData(String data){
        receivedData.concat(data + "\n");
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public String getReceivedData() {
        return receivedData;
    }

}
