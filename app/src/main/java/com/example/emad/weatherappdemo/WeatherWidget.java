package com.example.emad.weatherappdemo;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.Remote.WeatherApiProvider;
import com.google.gson.GsonBuilder;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherWidget extends AppWidgetProvider {

    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        final Cached mCached = new Cached(context);

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
                        updateViews(views, cityWeather);
                        mCached.cachedCityWeather(response.body());

                    } else {
                        CityWeather cityWeather = loadFromCached(response.raw().request(), mCached);
                        if (cityWeather != null)
                            updateViews(views, cityWeather);
                    }
                    appWidgetManager.updateAppWidget(appWidgetId, views);
                }

                @Override
                public void onFailure(Call<CityWeather> call, Throwable t) {
                    CityWeather cityWeather = loadFromCached(call.request(), mCached);
                    if (cityWeather != null) {
                        updateViews(views, cityWeather);
                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    }
                }
            });
        }
    }


    @SuppressLint("DefaultLocale")
    private void updateViews(RemoteViews views, CityWeather cityWeather) {
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
    }

    private CityWeather loadFromCached(Request request, Cached cached) {
        String id = request.url().queryParameter("id");
        String json = cached.getCached(Cached.TYPE_CITY_WEATHER, id);
        if (json != null) {
            return new GsonBuilder().create().fromJson(json, CityWeather.class);
        }
        return null;
    }
}