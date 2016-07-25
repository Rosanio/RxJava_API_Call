package com.epicodus.rxjavaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity {

    private Subscription mSubscription;
    private long mDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //According to the documentation, "Subscription returns from Observable.subscribe(Subscriber) to allow unsubscribing". This is why we can set it equal to the result of Observable.subscribe(). I'm not sure why we need to unsubscribe each time we make a call, but it seems like a good idea for some reason.
        if(mSubscription != null) {
            mSubscription.unsubscribe();
        }

        //This is our actual Retrofit object. This object will allow us to make the api call defined in our WeatherService interface.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                //I think this line allows our JSON response to convert into the classes we've defined
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //This line is a bit weird to me. It takes as its argument Interface.class. I'm not sure how or why this works, but my impression is that this actually sets up the groupList method defined in WeatherService to return an Observable which makes the API call. What I'd really like to figure out is what code is actually going into the Observables call method.
        WeatherService weatherService = retrofit.create(WeatherService.class);

        //Now that our groupList method returns an Observable, we can subscribe to it, which should cause it to make the call to the openWeather API. Once the call is made, we set our mDt variable using the WeatherData object that gets returned and Log it, to prove that it works. It'll do something much cooler later, I promise.
        mSubscription = weatherService.groupList("London", Constants.API_KEY)
                //This method causes the Observable to use a new thread to invoke its subscription (meaning it makes the API call on a new thread, I believe)
                .subscribeOn(Schedulers.newThread())
                //This method specifies that the Subscriber should observe the Observable on the main thread. I'm not 100% sure, but I think this means the onNext and/or onCompleted methods are called on the main thread.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public void onNext(WeatherData weather) {
                        MainActivity.this.mDt = weather.getDt();
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("weathers", mDt+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

    }


}
