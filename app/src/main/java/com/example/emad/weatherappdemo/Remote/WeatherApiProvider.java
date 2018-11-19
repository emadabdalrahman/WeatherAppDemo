package com.example.emad.weatherappdemo.Remote;


import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiProvider {

    private static String BASE_URI = "https://api.openweathermap.org/data/2.5/";

    public static Call<CityWeather> getCurrentWeather(String cityID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);
       return weatherApiService.getCurrentWeather(cityID);
    }

    public static Call<DailyForecast> getDailyForecast(String cityID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);
       return weatherApiService.getDailyForecast(cityID);
    }
}
