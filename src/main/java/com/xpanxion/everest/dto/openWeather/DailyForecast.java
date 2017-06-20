package com.xpanxion.everest.dto.openWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class to store the daily forecast data
 * 
 * @author Tiffany Fisher
 * created: 7/26/2016
 */
public class DailyForecast {

	/* list.dt Time of data forecasted */
	@JsonProperty("dt")
	private long dt;

	/*
	 * list.temp Holds various temperature values including: day, night, high,
	 * low, morning, and evening
	 */
	@JsonProperty("temp")
	private Temp temp;

	/* list.pressure Atmospheric pressure on the sea level, hPa */
	@JsonProperty("pressure")
	private double pressure;

	/* list.humidity Humidity, % */
	@JsonProperty("humidity")
	private int humidity;

	/*
	 * list.weather (more info Weather condition codes) including: id, main,
	 * description, and icon
	 */
	@JsonProperty("weather")
	private List<Weather> weather;

	/*
	 * list.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec,
	 * Imperial: miles/hour.
	 */
	@JsonProperty("speed")
	private double windSpeed;

	/* list.deg Wind direction, degrees (meteorological) */
	@JsonProperty("deg")
	private int windDirection;

	/* list.clouds Cloudiness, % */
	@JsonProperty("clouds")
	private int clouds;

	/* the predicted precipitation volume, mm */
	@JsonProperty("rain")
	private double rain;

	/**
	 * Get the time of data forecasted
	 * 
	 * @return the dt
	 */
	public long getDt() {
		return dt;
	}

	/**
	 * Set the time of data forecasted
	 * 
	 * @param dt
	 *            the date to set
	 */
	public void setDt(long dt) {
		this.dt = dt;
	}

	/**
	 * Get the temperature data
	 * 
	 * @return the temperature data
	 */
	public Temp getTemp() {
		return temp;
	}

	/**
	 * Set the temperature data
	 * 
	 * @param temp
	 *            the temperature data to set
	 */
	public void setTemp(Temp temp) {
		this.temp = temp;
	}

	/**
	 * Get the Atmospheric pressure on the sea level, hPa.
	 * 
	 * @return the pressure
	 */
	public double getPressure() {
		return pressure;
	}

	/**
	 * Set the Atmospheric pressure on the sea level, hPa
	 * 
	 * @param pressure
	 *            the pressure to set
	 */
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	/**
	 * Get the Humidity, %
	 * 
	 * @return the humidity
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * Set the Humidity, %
	 * 
	 * @param humidity
	 *            the humidity to set
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * Get the weather list
	 * 
	 * @return the weather
	 */
	public List<Weather> getWeather() {
		return weather;
	}

	/**
	 * Set the weather list
	 * 
	 * @param weather
	 *            the weather to set
	 */
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	/**
	 * Get the Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial:
	 * miles/hour.
	 * 
	 * @return the windSpeed
	 */
	public double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * Set the Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial:
	 * miles/hour.
	 * 
	 * @param windSpeed
	 *            the windSpeed to set
	 */
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * Get the Wind direction, degrees (meteorological)
	 * 
	 * @return the wind direction
	 */
	public int getWindDirection() {
		return windDirection;
	}

	/**
	 * Set the Wind direction, degrees (meteorological)
	 * 
	 * @param windDirection
	 *            the wind direction to set
	 */
	public void setWindDirection(int windDirection) {
		this.windDirection = windDirection;
	}

	/**
	 * Get the Cloudiness, %
	 * 
	 * @return the clouds
	 */
	public int getClouds() {
		return clouds;
	}

	/**
	 * Set the Cloudiness, %
	 * 
	 * @param clouds
	 *            the clouds to set
	 */
	public void setClouds(int clouds) {
		this.clouds = clouds;
	}

	/**
	 * Get the Precipitation volume predicted, mm
	 * 
	 * @return the predicted rain volume
	 */
	public double getRain() {
		return rain;
	}

	/**
	 * Set the Precipitation volume predicted, mm
	 * 
	 * @param rain
	 *            the rain volume to set
	 */
	public void setRain(double rain) {
		this.rain = rain;
	}
}
