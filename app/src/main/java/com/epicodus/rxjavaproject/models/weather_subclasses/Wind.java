package com.epicodus.rxjavaproject.models.weather_subclasses;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Container for JSON object "wind" returned in openWeather API response
 */
public class Wind {

    @SerializedName("speed")
    @Getter private float speed;

    @SerializedName("deg")
    @Getter private int deg;
}
