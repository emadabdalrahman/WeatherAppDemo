package com.example.emad.weatherappdemo.Fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emad.weatherappdemo.ForecastVModel;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;
import com.example.emad.weatherappdemo.R;

public class ForecastFragment extends Fragment {

    LiveData<DailyForecast> mForecastLiveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        ForecastVModel forecastVModel = ViewModelProviders.of(this).get(ForecastVModel.class);
        mForecastLiveData = forecastVModel.getDailyForecast();
        mForecastLiveData.observe(this, new Observer<DailyForecast>() {
            @Override
            public void onChanged(@Nullable DailyForecast dailyForecast) {

            }
        });

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mForecastLiveData.hasActiveObservers())
        mForecastLiveData.removeObservers(this);
    }
}
