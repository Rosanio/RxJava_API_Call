package com.epicodus.rxjavaproject.models.weather_subclasses;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Container for JSON object "sys" returned in openWeather API response
 */
public class Sys {

    @SerializedName("type")
    @Getter private int type;

    @SerializedName("id")
    @Getter private long id;

    @SerializedName("message")
    @Getter private float message;

    @SerializedName("country")
    @Getter private String country;

    @SerializedName("sunrise")
    @Getter private long sunrise;

    @SerializedName("sunset")
    @Getter private long sunset;
}
