package com.xpanxion.everest.dao.www;

import com.xpanxion.everest.dto.openWeather.*;
import com.xpanxion.everest.dto.weather.DayAfter;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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
    private String url;

    @Before
    public void setUp() {
        key = "the Key";
        url = "the Url";
        ReflectionTestUtils.setField(dao, "apiKey", key);
        ReflectionTestUtils.setField(dao, "openWeatherURL", url);
    }




    @Test
    public void test() {
        //given
        String code = "code";
        ForecastResponse response = getForecastResponse(12, "US/Eastern");
        when(restTemplate.getForObject(url, ForecastResponse.class, code, key)).thenReturn(response);

        long start = System.currentTimeMillis();
        //when
        com.xpanxion.everest.dto.weather.Weather output = dao.getWeather(code, "US/Eastern");

        long end = System.currentTimeMillis();

        //then
        assertThat(output.getCity(), is("Kearney"));
        assertThat(output.getRetrevalTime(), is(allOf(greaterThanOrEqualTo(start), lessThanOrEqualTo(end))));
        assertThat(output.getCondition(), is("description0"));
        assertThat(output.getCurrentTemp(), is("0.0"));
        assertThat(output.getTonightLow(), is( "-97.0"));
        assertThat(output.getTonightCondition(), is("description3"));
        assertThat(output.getTomorrowCondition(), is("description8"));
        assertThat(output.getTomorrowHigh(), is("108.0"));
        assertThat(output.getTomorrowLow(), is("-92.0"));
        assertThat(output.getDayAfterCondition(), is("description16"));

        DateTime dateTime = DateTime.now(DateTimeZone.UTC).withTime(12,0,0,0).plusHours(48).withZone(DateTimeZone.forID("US/Eastern"));
        assertThat(output.getDayAfterDate(), is(DayAfter.chooseDayAfter(DateTimeFormat.forPattern("EEE").print(dateTime))));
        assertThat(output.getDayAfterHigh(), is("116.0"));
        assertThat(output.getDayAfterLow(), is("-84.0"));
    }

    @Test
    public void testWithTonightDate() {
        //given
        String code = "code";
        ForecastResponse response = getForecastResponse(18, "US/Eastern");
        when(restTemplate.getForObject(url, ForecastResponse.class, code, key)).thenReturn(response);

        long start = System.currentTimeMillis();
        //when
        com.xpanxion.everest.dto.weather.Weather output = dao.getWeather(code, "US/Eastern");

        long end = System.currentTimeMillis();

        //then
        assertThat(output.getCity(), is("Kearney"));
        assertThat(output.getRetrevalTime(), is(allOf(greaterThanOrEqualTo(start), lessThanOrEqualTo(end))));
        assertThat(output.getCondition(), is("description0"));
        assertThat(output.getCurrentTemp(), is("0.0"));
        assertThat(output.getCurrentWeatherIcon(), is("xyz"));
        assertThat(output.getTonightLow(), is( "-99.0"));
        assertThat(output.getTonightCondition(), is("description1"));
        assertThat(output.getTomorrowCondition(), is("description8"));
        assertThat(output.getTomorrowHigh(), is("108.0"));
        assertThat(output.getTomorrowLow(), is("-92.0"));
        assertThat(output.getTomorrowWeatherIcon(), is("xyz"));
        assertThat(output.getDayAfterCondition(), is("description16"));
        assertThat(output.getDayAfterWeatherIcon(), is("xyz"));

        DateTime dateTime = DateTime.now(DateTimeZone.forID("US/Eastern")).withTime(18,0,0,0).plusHours(48);
        assertThat(output.getDayAfterDate(), is(DayAfter.chooseDayAfter(DateTimeFormat.forPattern("EEE").print(dateTime))));
        assertThat(output.getDayAfterHigh(), is("116.0"));
        assertThat(output.getDayAfterLow(), is("-84.0"));
    }


    private ForecastResponse getForecastResponse(int startingHour, String timeZone){
        ForecastResponse retVal = new ForecastResponse();


        City city = new City();
        city.setName("Kearney");
        retVal.setCity(city);
        retVal.setList(getForecastList(startingHour, timeZone));

        return retVal;
    }

    private List<Forecast> getForecastList(int startingHour, String timeZone){
        List<Forecast> retVal = new ArrayList<>();
        DateTime dateTime = DateTime.now(DateTimeZone.forID(timeZone));
        dateTime = dateTime.withTime(startingHour, 0, 0, 0);
        dateTime= dateTime.withZone(DateTimeZone.UTC);

        for(int i = 0 ; i < 20 ; i ++) {
            retVal.add(getForecast(i, dateTime.getMillis()));
            dateTime = dateTime.plusHours(3);
        }
        return retVal;
    }

    private Forecast getForecast(int index, long date){
        Forecast forecast = new Forecast();
        forecast.setDt(date/1000);
        Weather weather = new Weather();
        weather.setDescription("description" + index);
        weather.setIcon("xyz");
        forecast.setWeather(Arrays.asList(weather));

        Main main = new Main();
        main.setMaxTemp(100 + index);
        main.setMinTemp(-100 + index);
        main.setTemp(index);
        forecast.setMain(main);
        return forecast;
    }
}
