package com.xpanxion.everest.dto.openWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by brian on 3/31/16.
 */
public class Main {

    private double temp;
    @JsonProperty("temp_min")
    private double minTemp;
    @JsonProperty("temp_max")
    private double maxTemp;
    private double pressure;
    @JsonProperty("sea_level")
    private double seaLevel;
    @JsonProperty("grnd_level")
    private double groundLevel;
    private int humidity;

    public double getMinTemp() {
        return minTemp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setMinTemp(double minTemp) {

        this.minTemp = minTemp;
    }



}
