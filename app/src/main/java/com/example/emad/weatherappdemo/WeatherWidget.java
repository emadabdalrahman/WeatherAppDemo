package com.example.emad.weatherappdemo;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.emad.weatherappdemo.MainActivity.MainActivity;
import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.Remote.WeatherApiProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherWidget extends AppWidgetProvider {

    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i = 0; i < N; i++) {
            final int appWidgetId = appWidgetIds[i];

            final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            WeatherApiProvider.getCurrentWeather("361058").enqueue(new Callback<CityWeather>() {
                @SuppressLint("DefaultLocale")
                @Override
                public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {

                    if (response.isSuccessful()) {
                        CityWeather cityWeather = response.body();
                        Log.i("WeatherWidget", "onResponse: ");

                        views.setTextViewText(R.id.humidity_view_text,
                                String.format("%02d %%", cityWeather.getMain().getHumidity()));

                        int v = UnitConverter.convertMeterToKilo(cityWeather.getVisibility());

                        views.setTextViewText(R.id.visibility_view_text,
                                String.format("%02d KM", v));

                        views.setTextViewText(R.id.temperature_view_text_max_temp,
                                String.format("%02d", (int) cityWeather.getMain().getTempMax()));

                        views.setTextViewText(R.id.temperature_view_text_min_temp,
                                String.format("%02d", (int) cityWeather.getMain().getTempMin()));

                        views.setTextViewText(R.id.temperature_view_text_temp,
                                String.format("%02d", (int) cityWeather.getMain().getTemp()));

                        views.setTextViewText(R.id.main_weather_state_text,
                                cityWeather.getWeather().get(0).getDescription());

                        views.setImageViewResource(R.id.main_weather_state_img,
                                WeatherIconFactory.getIcon(cityWeather.getWeather().get(0).getIcon()));

                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    }
                }

                @Override
                public void onFailure(Call<CityWeather> call, Throwable t) {

                }
            });
        }
    }

}
