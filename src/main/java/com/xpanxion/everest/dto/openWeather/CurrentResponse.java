package com.xpanxion.everest.dto.openWeather;

import java.util.List;

/**
 * Current weather data response object as defined at https://openweathermap.org/current
 *
 * @author Tiffany Fisher
 * created: 7/26/2016
 */
public class CurrentResponse {
	/* coord Coordinates of the city's geolocation, latitude and longitude */
	private Coord coord;

	/* list of Weather condition codes */
	private List<Weather> weather;

	// base Internal parameter
	// cod Internal parameter

	/*
	 * main Contains temperature, atmospheric pressure, humidity, minimum &
	 * maximum temperatures at the moment, and atmospheric pressure on the sea &
	 * ground levels.
	 */
	private Main main;

	/* wind Contains wind speed and direction */
	private Wind wind;

	/* clouds Contains the percentage of cloud cover */
	private Clouds clouds;

	/* rain Contains the Rain volume for the last 3 hours */
	private Rain rain;

	/* snow Contains the Snow volume for the last 3 hours */
	private Snow snow;

	/* dt Time of data calculation, unix, UTC */
	private long dt;

	/* sys Contains the country, sunrise, and sunset times for the city */
	private Sys sys;

	/* id City ID */
	private int id;

	/* name City name */
	private String name;

	/**
	 * Get the city's coordinates
	 * 
	 * @return the coordinates
	 */
	public Coord getCoord() {
		return coord;
	}

	/**
	 * Set the city's coordinates
	 * @param coord the coordinates to set
	 */
	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	/**
	 * Gets the list of weather condition codes
	 * @return the weather
	 */
	public List<Weather> getWeather() {
		return weather;
	}

	/**
	 * Sets the list of weather condition codes
	 * @param weather the weather to set
	 */
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	/**
	 * Gets the main object which holds temperature, atmospheric pressure, humidity, minimum &
	 * maximum temperatures at the moment, and atmospheric pressure on the sea &
	 * ground levels conditions for the city
	 * @return the main
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Sets the main object
	 * @param main the main to set
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Get the wind data
	 * @return the wind
	 */
	public Wind getWind() {
		return wind;
	}

	/**
	 * Set the wind data
	 * @param wind the wind to set
	 */
	public void setWind(Wind wind) {
		this.wind = wind;
	}

	/**
	 * Get the cloudiness
	 * @return the clouds
	 */
	public Clouds getClouds() {
		return clouds;
	}

	/**
	 * Set the cloudiness
	 * @param clouds the clouds to set
	 */
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	/**
	 * Get the Rain volume for the last 3 hours
	 * @return the rain
	 */
	public Rain getRain() {
		return rain;
	}

	/**
	 * Set the Rain volume for the last 3 hours
	 * @param rain the rain to set
	 */
	public void setRain(Rain rain) {
		this.rain = rain;
	}

	/**
	 * Get the snow volume for the last 3 hours
	 * @return the snow
	 */
	public Snow getSnow() {
		return snow;
	}

	/**
	 * Set the snow volume for the last 3 hours
	 * @param snow the snow to set
	 */
	public void setSnow(Snow snow) {
		this.snow = snow;
	}

	/**
	 * Get the Unix time stamp for the data
	 * @return the dt
	 */
	public long getDt() {
		return dt;
	}

	/**
	 * Set the Unix time stamp for the data
	 * @param dt the dt to set
	 */
	public void setDt(long dt) {
		this.dt = dt;
	}

	/**
	 * Gets the sys data which hold additional data for the city
	 * @return the sys
	 */
	public Sys getSys() {
		return sys;
	}

	/**
	 * Sets the sys data which hold additional data for the city
	 * @param sys the sys to set
	 */
	public void setSys(Sys sys) {
		this.sys = sys;
	}

	/**
	 * Get the city ID
	 * @return the city ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the city ID
	 * @param id the city ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the city name
	 * @return the city name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the city name
	 * @param name the city name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
