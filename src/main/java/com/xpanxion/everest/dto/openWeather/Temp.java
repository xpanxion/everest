package com.xpanxion.everest.dto.openWeather;

/**
 * Class to hold the various forecast temperatures.
 * 
 * @author Tiffany Fisher
 * created: 7/26/2016
 */
public class Temp {
	/*
	 * list.temp.day Daily averaged temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 */
	private double day;

	/*
	 * list.temp.min Min daily temperature. Unit Default: Kelvin, Metric:
	 * Celsius, Imperial: Fahrenheit.
	 */
	private double min;

	/*
	 * list.temp.max Max daily temperature. Unit Default: Kelvin, Metric:
	 * Celsius, Imperial: Fahrenheit.
	 */
	private double max;

	/*
	 * list.temp.night Night temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 */
	private double night;

	/*
	 * list.temp.eve Evening temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 */
	private double eve;

	/*
	 * list.temp.morn Morning temperature. Unit Default: Kelvin, Metric:
	 * Celsius, Imperial: Fahrenheit.
	 */
	private double morn;

	/**
	 * Get the Averaged Day temperature. Unit Default: Kelvin, Metric: Celsius, Imperial:
	 * Fahrenheit.
	 * 
	 * @return the day temperature
	 */
	public double getDay() {
		return day;
	}

	/**
	 * Set the Averaged Day temperature. Unit Default: Kelvin, Metric: Celsius, Imperial:
	 * Fahrenheit.
	 * 
	 * @param day
	 *            the temperature to set
	 */
	public void setDay(double day) {
		this.day = day;
	}

	/**
	 * Get the Min daily temperature. Unit Default: Kelvin, Metric: Celsius, Imperial:
	 * Fahrenheit.
	 * 
	 * @return the min daily temperature
	 */
	public double getMin() {
		return min;
	}

	/**
	 * 
	 * set the min daily temperature. Unit Default: Kelvin, Metric: Celsius, Imperial:
	 * Fahrenheit.
	 * 
	 * @param min
	 *            the min daily temperature to set
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * Get the max daily temperature. Unit Default: Kelvin, Metric: Celsius, Imperial:
	 * Fahrenheit.
	 * 
	 * @return the max daily temperature
	 */
	public double getMax() {
		return max;
	}

	/**
	 * Set the max daily temperature. Unit Default: Kelvin, Metric: Celsius, Imperial:
	 * Fahrenheit.
	 * 
	 * @param max
	 *            the max daily temperature to set
	 */
	public void setMax(double max) {
		this.max = max;
	}

	/**
	 * Get the over-night temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 * 
	 * @return the over-night temperature
	 */
	public double getNight() {
		return night;
	}

	/**
	 * Set the over-night temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 * 
	 * @param night
	 *            the over-night temperature
	 */
	public void setNight(double night) {
		this.night = night;
	}

	/**
	 * Get the evening temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 * 
	 * @return the evening temperature
	 */
	public double getEve() {
		return eve;
	}

	/**
	 * Set the evening temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 * 
	 * @param eve
	 *            the evening temperature to set
	 */
	public void setEve(double eve) {
		this.eve = eve;
	}

	/**
	 * Get the morning temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 * 
	 * @return the morning temperature
	 */
	public double getMorn() {
		return morn;
	}

	/**
	 * set the morning temperature. Unit Default: Kelvin, Metric: Celsius,
	 * Imperial: Fahrenheit.
	 * 
	 * @param morn
	 *            the morning temperature to set
	 */
	public void setMorn(double morn) {
		this.morn = morn;
	}

}
