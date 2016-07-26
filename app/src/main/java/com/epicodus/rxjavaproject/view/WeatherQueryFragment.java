package com.epicodus.rxjavaproject.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.rxjavaproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This Fragment simply takes the users input and passes it to the MainActivity
 */
public class WeatherQueryFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.cityEditText) EditText mCityEditText;
    @Bind(R.id.queryButton) Button mQueryButton;
    private OnQuerySentListener mOnQuerySentListener;

    public WeatherQueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnQuerySentListener = (OnQuerySentListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_query, container, false);
        ButterKnife.bind(this, view);
        mQueryButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.queryButton:
                String city = mCityEditText.getText().toString();
                mOnQuerySentListener.sendQuery(city);
        }
    }
}
