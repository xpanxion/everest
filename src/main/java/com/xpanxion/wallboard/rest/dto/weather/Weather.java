package com.xpanxion.wallboard.rest.dto.weather;

public class Weather {

	private String city;
	private WeatherCondition condition;
	private String currentTemp;
	private WeatherCondition dayAfterCondition;
	private DayAfter dayAfterDate;
	private String dayAfterHigh;
	private String dayAfterLow;
	private long retrevalTime; // The time that this weather object was pulled
								// at.
	private WeatherCondition tomorrowCondition;
	private String tomorrowHigh;
	private String tomorrowLow;
	private String tonightLow;
	private WeatherCondition tonightWeatherIcon;

	/**
	 * @return the current city being used
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return current weather condition
	 */
	public WeatherCondition getCondition() {
		return this.condition;
	}

	/**
	 * @return the current temp
	 */
	public String getCurrentTemp() {
		return this.currentTemp;
	}

	/**
	 * @return expected weather condition for the day after tomorrow
	 */
	public WeatherCondition getDayAfterCondition() {
		return this.dayAfterCondition;
	}

	/**
	 * @return the name of the day after tomorrow
	 */
	public DayAfter getDayAfterDate() {
		return this.dayAfterDate;
	}

	/**
	 * @return expected high temp for the day after tomorrow
	 */
	public String getDayAfterHigh() {
		return this.dayAfterHigh;
	}

	/**
	 * @return expected low temp for the day after tomorrow
	 */
	public String getDayAfterLow() {
		return this.dayAfterLow;
	}

	/**
	 * @return the retrevalTime
	 */
	public long getRetrevalTime() {
		return this.retrevalTime;
	}

	/**
	 * @return expected weather condition for tomorrow
	 */
	public WeatherCondition getTomorrowCondition() {
		return this.tomorrowCondition;
	}

	/**
	 * @return expected high temp for tomorrow
	 */
	public String getTomorrowHigh() {
		return this.tomorrowHigh;
	}

	/**
	 * @return expected low temp for tomorrow
	 */
	public String getTomorrowLow() {
		return this.tomorrowLow;
	}

	/**
	 * Not used, but could be at some point
	 * 
	 * @return expected weather condition for the evening
	 */
	public WeatherCondition getTonightCondition() {
		return this.tonightWeatherIcon;
	}

	/**
	 * Not used, but could be at some point
	 * 
	 * @return expected low temp for the evening
	 */
	public String getTonightLow() {
		return this.tonightLow;
	}

	/**
	 * @param city
	 *            the current city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param condition
	 *            current weather condition to be set
	 */
	public void setCondition(WeatherCondition condition) {
		this.condition = condition;
	}

	/**
	 * @param current
	 *            the temp to be set
	 */
	public void setCurrentTemp(String currentTemp) {
		this.currentTemp = currentTemp;
	}

	/**
	 * @param dayAfterCondition
	 *            expected weather condition to be set
	 */
	public void setDayAfterCondition(WeatherCondition dayAfterCondition) {
		this.dayAfterCondition = dayAfterCondition;
	}

	/**
	 * @param dayAfterDate
	 *            the name of the day to be set
	 */
	public void setDayAfterDate(DayAfter dayAfterDate) {
		this.dayAfterDate = dayAfterDate;
	}

	/**
	 * @param dayAfterHigh
	 *            expected high temp to be set
	 */
	public void setDayAfterHigh(String dayAfterHigh) {
		this.dayAfterHigh = dayAfterHigh;
	}

	/**
	 * @param dayAfterLow
	 *            expected low temp to be set
	 */
	public void setDayAfterLow(String dayAfterLow) {
		this.dayAfterLow = dayAfterLow;
	}

	/**
	 * @param retrevalTime
	 *            the retrevalTime to set
	 */
	public void setRetrevalTime(long retrevalTime) {
		this.retrevalTime = retrevalTime;
	}

	/**
	 * @param tomorrowCondition
	 *            expected weather condition to be set
	 */
	public void setTomorrowCondition(WeatherCondition tomorrowCondition) {
		this.tomorrowCondition = tomorrowCondition;
	}

	/**
	 * @param tomorrowHigh
	 *            expected high temp to be set
	 */
	public void setTomorrowHigh(String tomorrowHigh) {
		this.tomorrowHigh = tomorrowHigh;
	}

	/**
	 * @param tomorrowLow
	 *            expected low temp to be set
	 */
	public void setTomorrowLow(String tomorrowLow) {
		this.tomorrowLow = tomorrowLow;
	}

	/**
	 * Not used, but could be at some point
	 * 
	 * @param tonightWeatherIcon
	 *            expected weather condition to be set
	 */
	public void setTonightCondition(WeatherCondition tonightWeatherIcon) {
		this.tonightWeatherIcon = tonightWeatherIcon;
	}

	/**
	 * Not used, but could be at some point
	 * 
	 * @param tonightLow
	 *            expected low temp to be set
	 */
	public void setTonightLow(String tonightLow) {
		this.tonightLow = tonightLow;
	}
}
