package com.xpanxion.everest.dao.www;

import java.util.List;
import java.util.TimeZone;

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

import com.xpanxion.everest.dao.WeatherDao;
import com.xpanxion.everest.dto.openWeather.CurrentResponse;
import com.xpanxion.everest.dto.openWeather.DailyForecast;
import com.xpanxion.everest.dto.openWeather.Forecast;
import com.xpanxion.everest.dto.openWeather.Forecast16Response;
import com.xpanxion.everest.dto.openWeather.ForecastResponse;
import com.xpanxion.everest.dto.weather.DayAfter;
import com.xpanxion.everest.dto.weather.Weather;

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

    @Value("${open.weather.dailyforecast.url}")
    private String openWeatherURLDaily;

    @Value("${open.weather.current.url}")
    private String openWeatherURLCurrent;

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
//        ForecastResponse response = weatherTemplate.getForObject(openWeatherURL, ForecastResponse.class, code, apiKey);
//        return convertToWeather(response, timeZone);
        Forecast16Response daily = getDailyConditions(code, timeZone);
        CurrentResponse current = getCurrentConditions(code, timeZone);

        return convertToWeather(current, daily, timeZone);
    }

    @Cacheable("weather")
    public Forecast16Response getDailyConditions(String code, String timeZone) {
        Forecast16Response response = weatherTemplate.getForObject(openWeatherURLDaily, Forecast16Response.class, code, apiKey);
        return response;
    }

    @Cacheable("weather")
    public CurrentResponse getCurrentConditions(String code, String timeZone) {
        CurrentResponse response = weatherTemplate.getForObject(openWeatherURLCurrent, CurrentResponse.class, code, apiKey);
        return response;
    }

    /**
     * Converts a response object to a Weather object
     *
     * @param response the response to convert from.
     * @param timeZone the time zone of the city that the response is for
     * @return the final Converted Weather object.
     */
    private Weather convertToWeather(CurrentResponse current, Forecast16Response daily, String timeZone) {
        Weather weather = new Weather();
        weather.setCity(current.getName());
        weather.setRetrevalTime(System.currentTimeMillis());

        weather.setCondition(current.getWeather().get(0).getDescription());
        weather.setCurrentTemp(Double.toString(current.getMain().getTemp()));
        weather.setCurrentWeatherIcon(current.getWeather().get(0).getIcon());

        DailyForecast tonight = daily.getList().get(0);
        weather.setTonightLow(Double.toString(tonight.getTemp().getNight()));
        //This is not quite right in the daily forecast.  It may be that we still need the hourly data.
        weather.setTonightWeatherIcon(tonight.getWeather().get(0).getIcon());

        DailyForecast tomorrow = daily.getList().get(1);
        weather.setTomorrowCondition(tomorrow.getWeather().get(0).getDescription());
        weather.setTomorrowHigh(Double.toString(tomorrow.getTemp().getMax()));
        weather.setTomorrowLow(Double.toString(tomorrow.getTemp().getMin()));
        weather.setTomorrowWeatherIcon(tomorrow.getWeather().get(0).getIcon());

        DailyForecast dayAfter = daily.getList().get(2);
        weather.setDayAfterCondition(dayAfter.getWeather().get(0).getDescription());
        weather.setDayAfterDate(getDayAfter(dayAfter.getDt() * 1000, timeZone));
        weather.setDayAfterHigh(Double.toString(dayAfter.getTemp().getMax()));
        weather.setDayAfterLow(Double.toString(dayAfter.getTemp().getMin()));
        weather.setDayAfterWeatherIcon(dayAfter.getWeather().get(0).getIcon());

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
