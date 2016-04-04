package com.xpanxion.everest.dao;

import com.xpanxion.everest.dto.weather.Weather;

/**
 * Dao that will retrieve weather information for a particular location.
 * 
 * @author bsmith
 */
public interface WeatherDao {
	/**
	 * Returns a weather object for a given code.
	 * 
	 * @param code
	 *            the code used to retrieve the weather.
	 * @param timeZone The time zone for the weather we are wanting.
	 * @return a weather object.
	 */
	Weather getWeather(String code, String timeZone);
}
