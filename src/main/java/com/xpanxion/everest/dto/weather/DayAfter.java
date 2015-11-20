package com.xpanxion.everest.dto.weather;

public enum DayAfter {

	Friday("Fri"), Monday("Mon"), Saturday("Sat"), Sunday("Sun"), Thursday(
			"Thu"), Tuesday("Tue"), Wednesday("Wed");

	private String given;

	private DayAfter(String given) {
		this.given = given;
	}

	/**
	 * Sets up the DayAfter date from the String given by Yahoo
	 * 
	 * @param code
	 *            shorthand given by Yahoo
	 * @return the DayAfter set up
	 */
	public DayAfter chooseDayAfter(String code) {
		DayAfter current = null;

		for (DayAfter day : DayAfter.values()) {
			if (day.getDay().equals(code)) {
				current = day;
			}
		}

		return current;
	}

	/**
	 * Allows user to access the DayAfter date
	 * 
	 * @return The name of the DayAfter date
	 */
	public String getDay() {
		return this.given;
	}

}
