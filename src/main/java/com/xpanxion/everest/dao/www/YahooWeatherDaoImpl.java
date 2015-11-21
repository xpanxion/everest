package com.xpanxion.everest.dao.www;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.xpanxion.everest.dao.WeatherDao;
import com.xpanxion.everest.dto.weather.Weather;
import com.xpanxion.everest.xml.WeatherFeedParser;

/**
 * 
 * @author bsmith
 */
@Repository
public class YahooWeatherDaoImpl implements WeatherDao {

	private static final Logger LOG = Logger.getLogger(YahooWeatherDaoImpl.class.getName());

	private Map<String, Weather> cache = new HashMap<String, Weather>();
	private Integer cacheTime = 20000;
	private String weatherURLPattern = "http://weather.yahooapis.com/forecastrss?p=%1$s&amp;u=f";

	@Override
	public Weather getWeather(String code) {
		Weather cacheValue = this.cache.get(code);
		if (cacheValue == null || (System.currentTimeMillis() - cacheValue.getRetrevalTime() > this.cacheTime)) {
			Weather weather = this.parseWeatherFromFeed(code);
			
			// do this so we don't delete an old but available cache value if the parsing fails for some reason
			if (null != weather) {
				this.cache.put(code, weather);
				cacheValue = weather;
			}
		}

		return cacheValue;
	}

	/**
	 * Parses the weather from the source feed.
	 * 
	 * @param code
	 *            the code to use to get the weather.
	 * @return the parsed weather.
	 */
	private Weather parseWeatherFromFeed(String code) {
		InputSource source = null;
		WeatherFeedParser parser = new WeatherFeedParser();
		try {
			source = new InputSource(new URL(String.format(this.weatherURLPattern, code)).openConnection().getInputStream());
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(parser);
			reader.parse(source);
		} catch (MalformedURLException e) {
			LOG.log(Level.SEVERE, "Unable to connect to weather feed", e);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Unable to connect to weather feed", e);
		} catch (SAXException e) {
			LOG.log(Level.SEVERE, "Unable parse to weather feed", e);

		} finally {
			if (source != null) {
				try {
					source.getByteStream().close();
				} catch (IOException e) {
					// nothing to do;
				}
			}
		}
		return parser.getWeather();
	}
	
	public void setCacheTime(int ms) {
		this.cacheTime = ms;
	}
	
	public void setWeatherURLPattern(String urlPattern) {
		this.weatherURLPattern = urlPattern;
	}
}
