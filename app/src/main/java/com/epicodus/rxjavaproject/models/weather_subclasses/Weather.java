package com.epicodus.rxjavaproject.models.weather_subclasses;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Container for JSON object "weather" returned in openWeather API response
 */
public class Weather {
    @SerializedName("id")
    @Getter private long id;

    @SerializedName("main")
    @Getter private String main;

    @SerializedName("description")
    @Getter private String description;

    @SerializedName("icon")
    @Getter private String icon;
}
