package com.example.emad.weatherappdemo.MainActivity.VModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.emad.weatherappdemo.Cached;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;
import com.example.emad.weatherappdemo.Remote.WeatherApiProvider;
import com.google.gson.GsonBuilder;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastVModel extends AndroidViewModel implements Callback<DailyForecast> {
    private String TAG = "ForecastVModel";
    private MutableLiveData<DailyForecast> mDailyForecast;
    private String mCityID;
    private Cached mCached;

    public ForecastVModel(@NonNull Application application) {
        super(application);
        mCached = new Cached(application.getApplicationContext());
    }

    public LiveData<DailyForecast> getDailyForecast(String cityID) {
        if (mDailyForecast == null) {
            mDailyForecast = new MutableLiveData<>();
            this.mCityID = cityID;
            loadDailyForecast();
        }
        return mDailyForecast;
    }

    private void loadDailyForecast() {
        WeatherApiProvider.getDailyForecast(mCityID).enqueue(this);
    }

    @Override
    public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
        if (response.isSuccessful()) {
            mDailyForecast.setValue(response.body());
            mCached.cachedForecast(response.body());
        } else {
            loadFromCached(response.raw().request());
        }
    }

    @Override
    public void onFailure(Call<DailyForecast> call, Throwable t) {
        Log.e(TAG, "onFailure: ", t);
        loadFromCached(call.request());
    }

    private void loadFromCached(Request request) {
        String json = mCached.getCached(Cached.TYPE_DAILY_FORECAST, request.url().queryParameter("id"));
        if (json != null) {
            mDailyForecast.setValue(new GsonBuilder().create().fromJson(json, DailyForecast.class));
        }
    }
}