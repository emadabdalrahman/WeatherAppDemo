package com.example.emad.weatherappdemo.MainActivity.VModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.emad.weatherappdemo.Cached;
import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.Remote.WeatherApiProvider;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeatherVModel extends AndroidViewModel implements Callback<CityWeather> {
    private String TAG = "CityWeatherVModel";
    private MutableLiveData<ArrayList<CityWeather>> mCurrentWeather;
    private ArrayList<CityWeather> mWeatherArrayList;
    private Cached mCached;
    private int count = 0;

    public CityWeatherVModel(@NonNull Application application) {
        super(application);
        mCached = new Cached(application.getApplicationContext());
    }

    public LiveData<ArrayList<CityWeather>> getCitesWeather() {
        if (mCurrentWeather == null) {
            mCurrentWeather = new MutableLiveData<>();
            mWeatherArrayList = new ArrayList<>(4);
            loadCitesWeather();
        }
        return mCurrentWeather;
    }

    private void loadCitesWeather() {
        WeatherApiProvider.getCurrentWeather("361058").enqueue(this);
        WeatherApiProvider.getCurrentWeather("519188").enqueue(this);
        WeatherApiProvider.getCurrentWeather("1283378").enqueue(this);
        WeatherApiProvider.getCurrentWeather("1270260").enqueue(this);
    }

    @Override
    public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {

        if (response.isSuccessful()) {
            mWeatherArrayList.add(response.body());
            mCached.cachedCityWeather(response.body());
        } else {
            loadFromCached(response.raw().request());
        }

        count++;
        if (count == 4) {
            mCurrentWeather.setValue(mWeatherArrayList);
        }

    }

    @Override
    public void onFailure(Call<CityWeather> call, Throwable t) {
        Log.e(TAG, "onFailure: ", t);
        count++;
        loadFromCached(call.request());
        if (count == 4 && mWeatherArrayList.size() != 0) {
            mCurrentWeather.setValue(mWeatherArrayList);
        }

    }

    private void loadFromCached(Request request) {
        String id = request.url().queryParameter("id");
        String json = mCached.getCached(Cached.TYPE_CITY_WEATHER, id);
        if (json != null)
            mWeatherArrayList.add(new GsonBuilder().create().fromJson(json, CityWeather.class));
    }
}
