package com.xpanxion.everest.dto.openWeather;

/**
 * Created by brian on 3/31/16.
 */
public class City {

    private String id;
    private String name;
    private Coord coord;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coor) {
        this.coord = coor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
