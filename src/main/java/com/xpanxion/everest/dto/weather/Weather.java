package com.xpanxion.everest.dto.weather;

public class Weather {

	private String city;
	private String condition;
	private String currentTemp;
	private String currentWeatherIcon;

	private String dayAfterCondition;
	private DayAfter dayAfterDate;
	private String dayAfterHigh;
	private String dayAfterLow;
	private String dayAfterWeatherIcon;

	private long retrievalTime; // The time that this weather object was pulled at.
	private String tomorrowCondition;
	private String tomorrowHigh;
	private String tomorrowLow;
	private String tomorrowWeatherIcon;

	private String tonightLow;
	private String tonightweatherIcon;

	/**
	 * @return the current city being used
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return current weather condition
	 */
	public String getCondition() {
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
	public String getDayAfterCondition() {
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
		return this.retrievalTime;
	}

	/**
	 * @return expected weather condition for tomorrow
	 */
	public String getTomorrowCondition() {
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
	public String getTonightWeatherIcon() {
		return this.tonightweatherIcon;
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
	public void setCondition(String condition) {
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
	public void setDayAfterCondition(String dayAfterCondition) {
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
		this.retrievalTime = retrevalTime;
	}

	/**
	 * @param tomorrowCondition
	 *            expected weather condition to be set
	 */
	public void setTomorrowCondition(String tomorrowCondition) {
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
	public void setTonightWeatherIcon(String tonightCondition) {
		this.tonightweatherIcon = tonightCondition;
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

	/**
	 * 
	 * @return The current weather's icon.
	 */
	public String getCurrentWeatherIcon() {
		return currentWeatherIcon;
	}

	/**
	 * 
	 * @param currentWeatherIcon The weather icon to set.
	 */
	public void setCurrentWeatherIcon(String currentWeatherIcon) {
		this.currentWeatherIcon = currentWeatherIcon;
	}

	/**
	 * 
	 * @return The day after weather's icon.
	 */
	public String getDayAfterWeatherIcon() {
		return dayAfterWeatherIcon;
	}

	/**
	 * 
	 * @param dayAfterWeatherIcon The weather icon to set.
	 */
	public void setDayAfterWeatherIcon(String dayAfterWeatherIcon) {
		this.dayAfterWeatherIcon = dayAfterWeatherIcon;
	}

	/**
	 * 
	 * @return The tomorrow weather's icon.
	 */
	public String getTomorrowWeatherIcon() {
		return tomorrowWeatherIcon;
	}

	/**
	 * 
	 * @param tomorrowWeatherIcon The weather icon to set.
	 */
	public void setTomorrowWeatherIcon(String tomorrowWeatherIcon) {
		this.tomorrowWeatherIcon = tomorrowWeatherIcon;
	}
	
	
}
