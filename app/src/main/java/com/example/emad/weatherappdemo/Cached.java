package com.example.emad.weatherappdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;

import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;
import com.google.gson.GsonBuilder;

public class Cached {

    public static final int TYPE_CITY_WEATHER = 0;
    public static final int TYPE_DAILY_FORECAST = 1;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public Cached(Context context) {
        mSharedPreferences = context.getSharedPreferences("Cached", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void cachedForecast(DailyForecast dailyForecast) {
        mEditor.putString(getKey(TYPE_DAILY_FORECAST, dailyForecast.getCity().getId()) + "LastUpdate",
                String.valueOf(SystemClock.elapsedRealtime()));
        mEditor.putString(getKey(TYPE_DAILY_FORECAST, dailyForecast.getCity().getId()),
                new GsonBuilder().create().toJson(dailyForecast));
        mEditor.apply();
    }

    public void cachedCityWeather(CityWeather cityWeather) {
        mEditor.putString(getKey(TYPE_CITY_WEATHER, cityWeather.getId()) + "LastUpdate",
                String.valueOf(SystemClock.elapsedRealtime()));
        mEditor.putString(getKey(TYPE_CITY_WEATHER, cityWeather.getId()),
                new GsonBuilder().create().toJson(cityWeather));
        mEditor.apply();
    }

    public String getCached(int type,String id) {
        return mSharedPreferences.getString(getKey(type,Integer.valueOf(id)), null);
    }

    private String getKey(int type, int id) {
        switch (type) {
            case TYPE_DAILY_FORECAST:
                return "DailyForecast" + String.valueOf(id);
            case TYPE_CITY_WEATHER:
                return "CityWeather" + String.valueOf(id);
            default:
                return null;
        }
    }

}
