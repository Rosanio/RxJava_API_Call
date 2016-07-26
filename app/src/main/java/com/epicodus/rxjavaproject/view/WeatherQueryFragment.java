package com.epicodus.rxjavaproject.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.rxjavaproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherQueryFragment extends Fragment {


    public WeatherQueryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_query, container, false);
    }

}
