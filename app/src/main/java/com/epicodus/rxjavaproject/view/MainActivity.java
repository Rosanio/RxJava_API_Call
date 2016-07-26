package com.epicodus.rxjavaproject.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.rxjavaproject.Constants;
import com.epicodus.rxjavaproject.R;
import com.epicodus.rxjavaproject.models.WeatherService;
import com.epicodus.rxjavaproject.models.WeatherData;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * An activity which is considered the "main" of it's peers
 */

public class MainActivity extends AppCompatActivity implements OnQuerySentListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public void sendQuery(String query) {
        WeatherListFragment listFrag = (WeatherListFragment) getSupportFragmentManager().findFragmentById(R.id.weatherListFragment);
        listFrag.getWeather(query);
    }


}
