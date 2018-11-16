package com.example.emad.weatherappdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;
import com.example.emad.weatherappdemo.Remote.WeatherApiProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastVModel extends ViewModel {
    private String TAG = "ForecastVModel";
    private MutableLiveData<DailyForecast> mDailyForecast;

    public LiveData<DailyForecast> getDailyForecast() {
        if (mDailyForecast == null) {
            mDailyForecast = new MutableLiveData<>();
            loadDailyForecast();
        }
        return mDailyForecast;
    }

    private void loadDailyForecast() {
        WeatherApiProvider.getDailyForecast().enqueue(new Callback<DailyForecast>() {
            @Override
            public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
                if (response.isSuccessful()) mDailyForecast.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DailyForecast> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

}
