package com.xpanxion.wallboard.rest.dto.weather;

public enum WeatherCondition {

	BLOWING_SNOW("15"), BLUSTERY("23"), CLEAR_NIGHT("31"), CLOUDY("26"), COLD(
			"25"), DRIZZLE("9"), DUST("19"), FAIR("34"), FAIR_NIGHT("33"), FOGGY(
			"20"), FREEZING_DRIZZLE("8"), FREEZING_RAIN("10"), HAIL("17"), HAZE(
			"21"), HEAVY_SNOW("41"), HEAVY_SNOW_SHOWERS("43"), HOT("36"), HURRICANE(
			"2"), ISOLATED_THUNDERSHOWERS("47"), ISOLATED_THUNDERSTORMS("37"), LIGHT_SNOW_SHOWERS(
			"14"), MOSTLY_CLOUDY("28"), MOSTLY_CLOUDY_NIGHT("27"), NOT_AVAILABLE(
			"3200"), PARTLY_CLOUDY("30"), PARTLY_CLOUDY_NIGHT("29"), RAIN_AND_HAIL(
			"35"), RAIN_AND_SLEET("6"), RAIN_AND_SNOW("5"), SCATTERED_SHOWERS(
			"40"), SCATTERED_SNOW_SHOWERS("42"), SCATTERED_THUNDERSTORM("39"), SCATTERED_THUNDERSTORMS(
			"38"), SEVERE_THUNDERSTORMS("3"), SHOWER("12"), SHOWERS("11"), SLEET(
			"18"), SLIGHTLY_CLOUDY("44"), SMOKY("22"), SNOW("16"), SNOW_AND_SLEET(
			"7"), SNOW_FLURRIES("13"), SNOW_SHOWERS("46"), SUNNY("32"), THUNDERSHOWERS(
			"45"), THUNDERSTORMS("4"), // conditions from Yahoo list
										// <http://developer.yahoo.com/weather/#codes>
	TORNADO("0"), TROPICAL_STORM("1"), WINDY("24");

	private String icon;

	private WeatherCondition(String icon) {
		this.icon = icon;
	}

	/**
	 * Set WeatherCondition from given Condition Code
	 * 
	 * @param code
	 *            the given code
	 * @return the set WeatherCondition
	 */
	public WeatherCondition chooseCondition(String code) {
		WeatherCondition condition = NOT_AVAILABLE;

		for (WeatherCondition weather : WeatherCondition.values()) {
			if (weather.getIcon().equals(code)) {
				condition = weather;
			} else {
				condition = NOT_AVAILABLE;
			}
		}

		return condition;
	}

	/**
	 * Allows user to access the Condition Code
	 * 
	 * @return the Condition Code
	 */
	public String getIcon() {
		return this.icon;
	}

}
