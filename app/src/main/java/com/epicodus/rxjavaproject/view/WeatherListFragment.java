package com.epicodus.rxjavaproject.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.rxjavaproject.Constants;
import com.epicodus.rxjavaproject.R;
import com.epicodus.rxjavaproject.models.WeatherData;
import com.epicodus.rxjavaproject.models.WeatherService;
import com.epicodus.rxjavaproject.models.weather_subclasses.Weather;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * This Fragment tells the presenter to make the API call and displays the data.
 */
public class WeatherListFragment extends Fragment {

    private Subscription mSubscription;
    private long mDt;

    public WeatherListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);



        return view;
    }

    public void getWeather(String city) {
        Log.d("city", city);
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

        mSubscription = weatherService.groupList(city, Constants.API_KEY)

                //This method causes the Observable to use a new thread to invoke its subscription (meaning it makes the API call on a new thread, I believe)

                .subscribeOn(Schedulers.newThread())

                //This method specifies that the Subscriber should observe the Observable on the main thread. I'm not 100% sure, but I think this means the onNext and/or onCompleted methods are called on the main thread.

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public void onNext(WeatherData weather) {
                        WeatherListFragment.this.mDt = weather.getDt();
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
