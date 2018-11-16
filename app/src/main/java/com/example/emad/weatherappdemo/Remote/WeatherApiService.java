package com.example.emad.weatherappdemo.Remote;

import com.example.emad.weatherappdemo.POJOs.CWeather.CurrentWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService  {

    String API_KEY = "56a67941c4c376d90bb1a6d9e97e6ff8";

    @GET("forecast/daily?appid="+API_KEY)
    Call<DailyForecast> getDailyForecast(@Query("id")String cityID);

    @GET("weather?appid="+API_KEY)
    Call<CurrentWeather> getCurrentWeather(@Query("id")String cityID);

}
