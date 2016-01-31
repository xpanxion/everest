/**
 * 
 */
package com.xpanxion.everest.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xpanxion.everest.dto.weather.DayAfter;
import com.xpanxion.everest.dto.weather.Weather;
import com.xpanxion.everest.dto.weather.WeatherCondition;

/**
 * @author bsmith
 * 
 */
public class WeatherFeedParser extends DefaultHandler {

	protected static final String CONDITION_ELEMENT = "yweather:condition";
	protected static final String FORCAST_ELEMENT = "yweather:forecast";
	protected static final String LOCATION_ELEMENT = "yweather:location";
	private WeatherCondition condition = WeatherCondition.NOT_AVAILABLE;
	private DayAfter current = null;
	private boolean nextDayDone = false;
	private boolean tomarrowDone = false;
	private boolean tonightDone = false;
	private Weather weather = null;

	public Weather getWeather() {
		return this.weather;
	}

	@Override
	public void startDocument() throws SAXException {
		this.weather = new Weather();
		this.weather.setRetrevalTime(System.currentTimeMillis());
	}

	@Override
	public void startElement(String arg0, String arg1, String weatherElement,
			Attributes attribute) throws SAXException {
		if (LOCATION_ELEMENT.equals(weatherElement)) {
			this.weather.setCity(attribute.getValue("city"));
		} else if (CONDITION_ELEMENT.equals(weatherElement)) {
			for (WeatherCondition weather : WeatherCondition.values()) {
				if (weather.getIcon().equals(attribute.getValue("code"))) {
					this.condition = weather;
				}
			}
			this.weather.setCondition(this.condition.getIcon());
			this.weather.setCurrentTemp(attribute.getValue("temp") + " F");
		} else if (FORCAST_ELEMENT.equals(weatherElement)) {
			if (!this.tonightDone) {
				this.weather.setTonightLow(attribute.getValue("low") + " F");
				for (WeatherCondition weather : WeatherCondition.values()) {
					if (weather.getIcon().equals(attribute.getValue("code"))) {
						this.condition = weather;
					}
				}
				this.weather.setTonightCondition(this.condition.getIcon());
				this.tonightDone = true;
			} else if (!this.tomarrowDone) {
				this.weather.setTomorrowHigh(attribute.getValue("high") + " F");
				this.weather.setTomorrowLow(attribute.getValue("low") + " F");
				for (WeatherCondition weather : WeatherCondition.values()) {
					if (weather.getIcon().equals(attribute.getValue("code"))) {
						this.condition = weather;
					}
				}
				this.weather.setTomorrowCondition(this.condition.getIcon());
				this.tomarrowDone = true;
			} else if (!this.nextDayDone) {
				this.weather.setDayAfterHigh(attribute.getValue("high") + " F");
				this.weather.setDayAfterLow(attribute.getValue("low") + " F");
				for (WeatherCondition weather : WeatherCondition.values()) {
					if (weather.getIcon().equals(attribute.getValue("code"))) {
						this.condition = weather;
					}
				}
				this.weather.setDayAfterCondition(this.condition.getIcon());
				for (DayAfter day : DayAfter.values()) {
					if (day.getDay().equals(attribute.getValue("day"))) {
						this.current = day;
					}
				}
				this.weather.setDayAfterDate(this.current);
				this.nextDayDone = true;
			}
		}

	}
}
