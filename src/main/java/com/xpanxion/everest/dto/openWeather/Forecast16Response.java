package com.xpanxion.everest.dto.openWeather;

import java.util.List;

/**
 * 16 day / daily forecast data response object as defined at https://openweathermap.org/forecast16
 *
 * @author Tiffany Fisher
 * created: 7/26/2016
 */
public class Forecast16Response {

    private City city;
    private int cnt;
    private List<DailyForecast> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<DailyForecast> getList() {
        return list;
    }

    public void setList(List<DailyForecast> list) {
        this.list = list;
    }
}
