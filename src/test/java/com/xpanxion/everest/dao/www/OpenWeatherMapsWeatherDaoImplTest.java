package com.xpanxion.everest.dao.www;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.xpanxion.everest.dto.openWeather.CurrentResponse;
import com.xpanxion.everest.dto.openWeather.DailyForecast;
import com.xpanxion.everest.dto.openWeather.Forecast16Response;
import com.xpanxion.everest.dto.openWeather.ForecastResponse;
import com.xpanxion.everest.dto.openWeather.Main;
import com.xpanxion.everest.dto.openWeather.Temp;
import com.xpanxion.everest.dto.openWeather.Weather;
import com.xpanxion.everest.dto.weather.DayAfter;

/**
 * Created by brian on 3/31/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapsWeatherDaoImplTest {

    @InjectMocks
    private OpenWeatherMapsWeatherDaoImpl dao;

    @Mock
    private RestTemplate restTemplate;

    private String key;
    private String dailyUrl;
    private String forcastUrl;

    @Before
    public void setUp() {
        key = "the Key";
        dailyUrl = "the daily Url";
        forcastUrl = "the forcastUrl";
        ReflectionTestUtils.setField(dao, "apiKey", key);
        ReflectionTestUtils.setField(dao, "openWeatherURLDaily", forcastUrl);
        ReflectionTestUtils.setField(dao, "openWeatherURLCurrent", dailyUrl);
    }




    @Test
    public void test() {
        //given
        String code = "code";
        Forecast16Response response = getForecast16("US/Eastern", 12);
        when(restTemplate.getForObject(forcastUrl, Forecast16Response.class, code, key)).thenReturn(response);

        CurrentResponse currentResponse = getCurrentResponse();
        when(restTemplate.getForObject(dailyUrl, CurrentResponse.class, code, key)).thenReturn(currentResponse);
               
        long start = System.currentTimeMillis();
        //when
        com.xpanxion.everest.dto.weather.Weather output = dao.getWeather(code, "US/Eastern");

        long end = System.currentTimeMillis();

        //then
        assertThat(output.getCity(), is("Kearney"));
        assertThat(output.getRetrevalTime(), is(allOf(greaterThanOrEqualTo(start), lessThanOrEqualTo(end))));
        assertThat(output.getCondition(), is("description0"));
        assertThat(output.getCurrentTemp(), is("0.0"));
        assertThat(output.getCurrentWeatherIcon(), is("icon0"));
        assertThat(output.getTonightLow(), is( "50.0"));
        assertThat(output.getTonightWeatherIcon(), is("icon0"));
        assertThat(output.getTomorrowCondition(), is("description1"));
        assertThat(output.getTomorrowHigh(), is("101.0"));
        assertThat(output.getTomorrowLow(), is("-99.0"));
        assertThat(output.getTomorrowWeatherIcon(), is("icon1"));
        assertThat(output.getDayAfterCondition(), is("description2"));
        assertThat(output.getDayAfterWeatherIcon(), is("icon2"));

        DateTime dateTime = DateTime.now(DateTimeZone.UTC).withTime(12,0,0,0).plusHours(48).withZone(DateTimeZone.forID("US/Eastern"));
        assertThat(output.getDayAfterDate(), is(DayAfter.chooseDayAfter(DateTimeFormat.forPattern("EEE").print(dateTime))));
        assertThat(output.getDayAfterHigh(), is("102.0"));
        assertThat(output.getDayAfterLow(), is("-98.0"));
    }

    @Test
    public void testWithTonightDate() {
        //given
        String code = "code";
        Forecast16Response response = getForecast16("US/Eastern", 12);
        when(restTemplate.getForObject(forcastUrl, Forecast16Response.class, code, key)).thenReturn(response);

        CurrentResponse currentResponse = getCurrentResponse();
        when(restTemplate.getForObject(dailyUrl, CurrentResponse.class, code, key)).thenReturn(currentResponse);
               
        long start = System.currentTimeMillis();
        //when
        com.xpanxion.everest.dto.weather.Weather output = dao.getWeather(code, "US/Eastern");

        long end = System.currentTimeMillis();

        //then
        assertThat(output.getCity(), is("Kearney"));
        assertThat(output.getRetrevalTime(), is(allOf(greaterThanOrEqualTo(start), lessThanOrEqualTo(end))));
        assertThat(output.getCondition(), is("description0"));
        assertThat(output.getCurrentTemp(), is("0.0"));
        assertThat(output.getCurrentWeatherIcon(), is("icon0"));
        assertThat(output.getTonightLow(), is( "50.0"));
        assertThat(output.getTonightWeatherIcon(), is("icon0"));
        assertThat(output.getTomorrowCondition(), is("description1"));
        assertThat(output.getTomorrowHigh(), is("101.0"));
        assertThat(output.getTomorrowLow(), is("-99.0"));
        assertThat(output.getTomorrowWeatherIcon(), is("icon1"));
        assertThat(output.getDayAfterCondition(), is("description2"));
        assertThat(output.getDayAfterWeatherIcon(), is("icon2"));

        DateTime dateTime = DateTime.now(DateTimeZone.forID("US/Eastern")).withTime(12,0,0,0).plusHours(48);
        assertThat(output.getDayAfterDate(), is(DayAfter.chooseDayAfter(DateTimeFormat.forPattern("EEE").print(dateTime))));
        assertThat(output.getDayAfterHigh(), is("102.0"));
        assertThat(output.getDayAfterLow(), is("-98.0"));
    }


    private CurrentResponse getCurrentResponse(){
        CurrentResponse retVal = new CurrentResponse();


        retVal.setName("Kearney");
        retVal.setWeather(Arrays.asList(getWeather(0)));
        
        Main main = new Main();
        main.setTemp(0);
        retVal.setMain(main);
        
        return retVal;
    }
    
    private Forecast16Response getForecast16(String timezone, int currentHour){
    	Forecast16Response response = new Forecast16Response();
    	response.setList(getForecastList(timezone, currentHour));
    	return response;
    }
    
    

    private List<DailyForecast> getForecastList(String timezone, int currentHour){
        List<DailyForecast> retVal = new ArrayList<>();

        for(int i = 0 ; i < 16 ; i ++) {
            retVal.add(getForecast(i, timezone, currentHour));
        }
        return retVal;
    }

    private DailyForecast getForecast(int index, String timeZone, int currentHour){
        DailyForecast forecast = new DailyForecast();
        forecast.setWeather(Arrays.asList(getWeather(index)));

        DateTime dateTime = DateTime.now(DateTimeZone.forID(timeZone));
        dateTime = dateTime.withTime(currentHour, 0, 0, 0).plusHours(index * 24);
        dateTime= dateTime.withZone(DateTimeZone.UTC);

        forecast.setDt(dateTime.getMillis()/1000);
        
        Temp temp = new Temp();
        temp.setNight(50 + index);
        temp.setMax(100 + index);
        temp.setMin(-100 + index);
        forecast.setTemp(temp);

        return forecast;
    }
    
    private Weather getWeather(int index){
        Weather weather = new Weather();
        weather.setDescription("description" + index);
        weather.setIcon("icon" + index);
        return weather;
    }
}
