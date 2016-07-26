package com.epicodus.rxjavaproject.models;

import com.epicodus.rxjavaproject.models.WeatherData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Interface which contains data needed to create url to call openWeather API
 */

public interface WeatherService {

    //This @GET thing continues where the baseUrl left off (baseUrl located in MainActivity where the Retrofit is constructed). I'm not sure exactly what the proper syntax is as far as how to separate the URL, but I think the base URL is not meant to end with a /. Otherwise, as long as you include everything up to the ?, it seems to work fine.

    @GET("/data/2.5/weather?")

    //This is a method declaration. The syntax kind of confused me before I realized it's defined in an interface, meaning there's not actually any code to execute. This method returns an observable, which is somehow used to make the API call. I'll update this description once I figure out how.
    Observable<WeatherData> groupList(@Query("q") String city, @Query("appid") String key);
}
