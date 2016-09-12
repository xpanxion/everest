package com.xpanxion.everest.dao.www;

import com.xpanxion.everest.dao.WeatherDao;
import com.xpanxion.everest.dto.openWeather.Forecast;
import com.xpanxion.everest.dto.openWeather.ForecastResponse;
import com.xpanxion.everest.dto.weather.DayAfter;
import com.xpanxion.everest.dto.weather.Weather;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.TimeZone;

/**
 * Implementation of Weather Dao that uses OpenWeatherMaps and their forecast5 api http://openweathermap.org/forecast5
 */
@Component("OpenWeather")
public class OpenWeatherMapsWeatherDaoImpl implements WeatherDao {

    private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapsWeatherDaoImpl.class);

    private static final String ICON_URL_FMT = "http://openweathermap.org/img/w/%s.png";
    private static final String DAY_0F_WEEK = "EEE";

    @Value("${open.weather.forcast.url}")
    private String openWeatherURL;

    @Value("${open.weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate weatherTemplate;

    /**
     * Calls open weather maps to get the weather
     * @param code City Code to get the weather from.  City codes can be found here. http://bulk.openweathermap.org/sample/city.list.json.gz
     * @param timeZone TimeZone that the city resides in
     * @return A loaded weather object
     */
    @Override
    @Cacheable("weather")
    public Weather getWeather(String code, String timeZone) {
        ForecastResponse response = weatherTemplate.getForObject(openWeatherURL, ForecastResponse.class, code, apiKey);
        return convertToWeather(response, timeZone);
    }

    /**
     * Converts a response object to a Weather object
     *
     * @param response the response to convert from.
     * @param timeZone the time zone of the city that the response is for
     * @return the final Converted Weather object.
     */
    private Weather convertToWeather(ForecastResponse response, String timeZone) {
        Weather weather = new Weather();
        weather.setCity(response.getCity().getName());
        weather.setRetrevalTime(System.currentTimeMillis());

        //0 is current conditions
        Forecast current = response.getList().get(0);
        weather.setCondition(current.getWeather().get(0).getDescription());
        weather.setCurrentTemp(Double.toString(current.getMain().getTemp()));
        weather.setCurrentWeatherIcon(String.format(ICON_URL_FMT, current.getWeather().get(0).getIcon()));

        Forecast tonight = getTonightForecast(response.getList(), current.getDt() * 1000, timeZone);
        weather.setTonightLow(Double.toString(tonight.getMain().getMinTemp()));
        weather.setTonightWeatherIcon(String.format(ICON_URL_FMT, tonight.getWeather().get(0).getIcon()));

        //forecasts are given in 3 hour increments so 24 hours from now would be 24 / 3 or 8.
        Forecast tomorrow = response.getList().get(8);
        weather.setTomorrowCondition(tomorrow.getWeather().get(0).getDescription());
        weather.setTomorrowHigh(Double.toString(tomorrow.getMain().getMaxTemp()));
        weather.setTomorrowLow(Double.toString(tomorrow.getMain().getMinTemp()));
        weather.setTomorrowWeatherIcon(String.format(ICON_URL_FMT, tomorrow.getWeather().get(0).getIcon()));

        Forecast dayAfter = response.getList().get(16);
        weather.setDayAfterCondition(dayAfter.getWeather().get(0).getDescription());
        weather.setDayAfterDate(getDayAfter(dayAfter.getDt() * 1000, timeZone));
        weather.setDayAfterHigh(Double.toString(dayAfter.getMain().getMaxTemp()));
        weather.setDayAfterLow(Double.toString(dayAfter.getMain().getMinTemp()));
        weather.setDayAfterWeatherIcon(String.format(ICON_URL_FMT, dayAfter.getWeather().get(0).getIcon()));

        return weather;
    }

    /**
     * Returns the forecast object for "tonight"  tonight is chosen to be the forecast occurrence closest to
     * midnight price-is-right style the timezone
     *
     * @param forecasts The list of forecasts to look through
     * @param todayDate Today's Date or the date of item 0 in the forecasts.
     * @param timeZone The time zone that we are looking at forecasts for
     * @return the forecast deemed as "tonight's" forecast.
     */
    private Forecast getTonightForecast(List<Forecast> forecasts, long todayDate, String timeZone){
        long tonightDate = getTonightDate(todayDate, timeZone);
        Forecast retVal = null;
        for(Forecast forecast : forecasts) {
            if( tonightDate  - forecast.getDt() * 1000 <= (3 * 60 * 60 *1000) ){ //if the difference is <= 3 hours
                retVal = forecast;
                break;
            }
        }
        return retVal;
    }


    /**
     * Returns Tonight's date in millis.  Tonight is deemed to be midnight following the passed in date in the passed in timezone.
     * @param date  Starting time in millis
     * @param timeZone The time zone to find midnight in.
     * @return Midnight following the passed in date in the passed in timezone
     */
    private long getTonightDate(long date, String timeZone) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));

        dateTime = dateTime.plusDays(1);
        //set to midnight and return the UTC milis
        return  dateTime.withTime(0,0,0,0).withZone(DateTimeZone.UTC).getMillis();

    }

    /**
     * Locates the day after object for the passed in time in the passed in timezone
     * @param time the initial time.
     * @param timeZone the time zone we are concerned with.
     * @return The DayAfter object associated to the passed in parameters.
     */
    private DayAfter getDayAfter(long time, String timeZone){
        DateTime dateTime = new DateTime().withMillis(time).withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DAY_0F_WEEK);
        return DayAfter.chooseDayAfter(formatter.print(dateTime));
    }

}
