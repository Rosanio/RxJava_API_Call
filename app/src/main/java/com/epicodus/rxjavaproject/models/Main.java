package com.epicodus.rxjavaproject.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Container for JSON object "main" returned in openWeather API response
 */
public class Main {
    @SerializedName("temp")
    @Getter private float temp;

    @SerializedName("pressure")
    @Getter private float pressure;

    @SerializedName("humidity")
    @Getter private float humidity;

    @SerializedName("temp_min")
    @Getter private float minTemp;

    @SerializedName("temp_max")
    @Getter private float maxTemp;
}
