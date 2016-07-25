package com.epicodus.rxjavaproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

/**
 * Container to hold data returned from call to openWeather API. As a note, everything returned in JSON object must be accounted for
 */
public class WeatherData {

    //@SerializedName, which comes from the gson library, is actually not necessary here. If the name of the variable matches the name of the JSON key, we don't need to use it. @SerializedName allows you to name the actual variable something else. For example, if I wanted this Coord to be called mCoord, I would write @SerializedName("coord") private Coord mCoord.

    @SerializedName("coord")

    //@Getter comes from the lombok library. It allows me to call getCoord() as if I had written a standard getter, just without actually writing the code. Saves 3 lines per variable.

    @Getter private Coord coord;

    @SerializedName("weather")
    @Getter private List<Weather> weather;

    @SerializedName("base")
    @Getter private String base;

    @SerializedName("main")
    @Getter private Main main;

    @SerializedName("visibility")
    @Getter private long visibility;

    @SerializedName("wind")
    @Getter private Wind wind;

    @SerializedName("clouds")
    @Getter private Clouds clouds;

    @SerializedName("dt")
    @Getter private long dt;

    @SerializedName("sys")
    @Getter private Sys sys;

    @SerializedName("id")
    @Getter private long id;

    @SerializedName("name")
    @Getter private String name;

    @SerializedName("cod")
    @Getter private int cod;
}
