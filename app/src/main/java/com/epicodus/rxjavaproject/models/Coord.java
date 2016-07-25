package com.epicodus.rxjavaproject.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Container for JSON object "coord" returned in openWeather API response
 */
public class Coord {
    @SerializedName("lon")
    @Getter private float lon;

    @SerializedName("lat")
    @Getter private float lat;
}
