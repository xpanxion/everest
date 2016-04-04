package com.xpanxion.everest.dto.openWeather;

import java.util.List;

/**
 * Response object as defined at http://openweathermap.org/forecast5
 *
 */
public class ForecastResponse {

    private City city;
    private int cnt;
    private List<Forecast> list;

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

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }
}
