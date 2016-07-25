package com.epicodus.rxjavaproject.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Container for JSON object "clouds" returned in openWeather API response
 */
public class Clouds {

    @SerializedName("all")
    @Getter private float all;
}
