package com.example.emad.weatherappdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.emad.weatherappdemo.POJOs.CWeather.CurrentWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;
import com.example.emad.weatherappdemo.Remote.WeatherApiProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherVModel extends ViewModel {
    private String TAG = "WeatherVModel";
    private MutableLiveData<CurrentWeather> mCurrentWeather;

    public LiveData<CurrentWeather> getDailyForecast() {
        if (mCurrentWeather == null) {
            mCurrentWeather = new MutableLiveData<>();
            loadDailyForecast();
        }
        return mCurrentWeather;
    }

    private void loadDailyForecast() {
        WeatherApiProvider.getCurrentWeather().enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful()) mCurrentWeather.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

}
