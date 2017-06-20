package com.xpanxion.everest.dto.openWeather;

/**
 * Class to hold the country, sunrise, and sunset data for the city used by the CurrentResponse.
 * 
 * @author Tiffany Fisher
 * 
 * created date: 7/27/2016
 */
public class Sys {

	// sys.type Internal parameter
	// sys.id Internal parameter
	// sys.message Internal parameter
	
	/* sys.country Country code (GB, JP etc.) */
	private String country;

	/* sys.sunrise Sunrise time, unix, UTC */
	private long sunrise;

	/* sys.sunset Sunset time, unix, UTC */
	private long sunset;

	/**
	 * Gets the country
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country
	 * 
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Get the Unix time stamp for the sunrise
	 * 
	 * @return the sunrise
	 */
	public long getSunrise() {
		return sunrise;
	}

	/**
	 * Set the Unix time stamp for the sunrise
	 * 
	 * @param sunrise
	 *            the sunrise to set
	 */
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}

	/**
	 * Get the Unix time stamp for the sunset
	 * 
	 * @return the sunset
	 */
	public long getSunset() {
		return sunset;
	}

	/**
	 * Set the Unix time stamp for the sunset
	 * 
	 * @param sunset
	 *            the sunset to set
	 */
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
}
