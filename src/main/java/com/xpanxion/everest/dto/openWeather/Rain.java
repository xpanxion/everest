package com.xpanxion.everest.dto.openWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by brian on 3/31/16.
 */
public class Rain {

    @JsonProperty("3h")
    private int threeHours;

    public int getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(int threeHours) {
        this.threeHours = threeHours;
    }
}
