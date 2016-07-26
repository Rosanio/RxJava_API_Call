package com.epicodus.rxjavaproject.view;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.rxjavaproject.Constants;
import com.epicodus.rxjavaproject.R;
import com.epicodus.rxjavaproject.WeatherContract;
import com.epicodus.rxjavaproject.models.WeatherData;
import com.epicodus.rxjavaproject.models.WeatherService;
import com.epicodus.rxjavaproject.models.weather_subclasses.Weather;
import com.epicodus.rxjavaproject.presenter.WeatherPresenter;

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
public class WeatherListFragment extends Fragment implements WeatherContract.View {

    private WeatherContract.Presenter mPresenter;

    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.temperatureTextView) TextView mTemperatureTextView;
    @Bind(R.id.windTextView) TextView mWindSpeedTextView;

    public WeatherListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);

        ButterKnife.bind(this, view);
        mPresenter = new WeatherPresenter(this);
        return view;
    }

    public void getWeather(String city) {
        Log.d("city", city);
        mPresenter.getWeather(city);

    }

    @Override
    public void showWeather(String description, float temp, float windSpeed) {
        Resources res = getResources();
        String descriptionText = String.format(res.getString(R.string.weather_description), description);
        mDescriptionTextView.setText(descriptionText);

        String tempText = String.format(res.getString(R.string.weather_temp), temp);
        mTemperatureTextView.setText(tempText);

        String windText = String.format(res.getString(R.string.weather_wind), windSpeed);
        mWindSpeedTextView.setText(windText);
    }

}
