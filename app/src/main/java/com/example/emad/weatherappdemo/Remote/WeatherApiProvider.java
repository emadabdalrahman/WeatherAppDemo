package com.example.emad.weatherappdemo.Remote;


import com.example.emad.weatherappdemo.POJOs.CWeather.CurrentWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiProvider {

    private static String BASE_URI = "https://api.openweathermap.org/data/2.5/";

    public static Call<CurrentWeather> getCurrentWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);
       return weatherApiService.getCurrentWeather("361058");
    }

    public static Call<DailyForecast> getDailyForecast() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);
       return weatherApiService.getDailyForecast("361058");
    }
}
