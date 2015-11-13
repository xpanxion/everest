package com.xpanxion.wallboard.rest.dao;

import com.xpanxion.wallboard.rest.dto.weather.Weather;

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
	 * @return a weather object.
	 */
	Weather getWeather(String code);
}
