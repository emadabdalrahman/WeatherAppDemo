package com.example.emad.weatherappdemo.MainActivity.Fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emad.weatherappdemo.MainActivity.CityAdapter;
import com.example.emad.weatherappdemo.MainActivity.ForecastAdapter;
import com.example.emad.weatherappdemo.MainActivity.VModel.ForecastVModel;
import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.DailyForecast;
import com.example.emad.weatherappdemo.POJOs.DForecast.Forecast;
import com.example.emad.weatherappdemo.R;
import com.example.emad.weatherappdemo.ToolbarShadowView;
import com.example.emad.weatherappdemo.UnitConverter;
import com.example.emad.weatherappdemo.WeatherIconFactory;
import com.github.ahmadnemati.wind.WindView;
import com.github.ahmadnemati.wind.enums.TrendType;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.fragment.NavHostFragment;

public class ForecastFragment extends Fragment implements ForecastAdapter.ItemListener {

    LiveData<DailyForecast> mForecastLiveData;
    ForecastAdapter mForecastAdapter;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);


        initRecyclerView(rootView);
        initToolbar(rootView);
        ForecastVModel forecastVModel = ViewModelProviders.of(this).get(ForecastVModel.class);
        mForecastLiveData = forecastVModel.getDailyForecast(String.valueOf(getArguments().getInt("CityID")));
        mForecastLiveData.observe(this, new Observer<DailyForecast>() {
            @Override
            public void onChanged(@Nullable DailyForecast dailyForecast) {
            //    String test = dailyForecast.toString();

                updateRecycler(dailyForecast.getList());
                updateHeader(rootView, dailyForecast.getList().get(0));
            }
        });

        return rootView;
    }

    private void updateHeader(View rootView, Forecast forecast) {

        TextView temp = (TextView) rootView.findViewById(R.id.temperature_text);
        TextView tempMax = (TextView) rootView.findViewById(R.id.temperature_view_text_max_temp);
        TextView tempMin = (TextView) rootView.findViewById(R.id.temperature_view_text_min_temp);
        TextView visibility = (TextView) rootView.findViewById(R.id.visibility_text);
        TextView humidity = (TextView) rootView.findViewById(R.id.humidity_text);
        TextView weatherState = (TextView) rootView.findViewById(R.id.main_weather_state_text);
        WindView windView = (WindView) rootView.findViewById(R.id.windview);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.main_weather_state_img);


        windView.setPressure(UnitConverter.convertHpaToHg(forecast.getPressure()));
        windView.setPressureUnit("in Hg");
        windView.setWindSpeed(forecast.getSpeed());
        windView.setWindSpeedUnit(" M/s");
        windView.setTrendType(TrendType.UP);
        windView.start();

        temp.setText(String.format("%02d", (int) forecast.getTemp().getEve()));
        tempMin.setText(String.format("%02d", (int) forecast.getTemp().getMin()));
        tempMax.setText(String.format("%02d", (int) forecast.getTemp().getMax()));
        humidity.setText(String.format("%02d %%", forecast.getHumidity()));
        visibility.setText(String.format("%02d KM", UnitConverter.convertMeterToKilo(getArguments().getInt("Visibility"))));
        weatherState.setText(forecast.getWeather().get(0).getDescription());
        imageView.setImageResource(WeatherIconFactory.getIcon(forecast.getWeather().get(0).getIcon()));

    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.forecast_recycler);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setRecycleChildrenOnDetach(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void updateRecycler(List<Forecast> forecast) {
        if (mForecastAdapter == null) {
            mForecastAdapter = new ForecastAdapter(forecast, this);
            mRecyclerView.setAdapter(mForecastAdapter);
        } else {
            mForecastAdapter.updateDataSet(forecast);
        }
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.forecast_fg_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ToolbarShadowView toolbarShadowView = view.findViewById(R.id.forecast_fg_elevationView);
        toolbarShadowView.setRecyclerView(mRecyclerView);
        toolbarShadowView.setLineDivider(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ForecastFragment.this).popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mForecastLiveData.hasActiveObservers())
            mForecastLiveData.removeObservers(this);

        mRecyclerView.setAdapter(null);
        if (mForecastAdapter != null)
            mForecastAdapter.setItemListener(null);
        mForecastAdapter = null;
    }

    @Override
    public void onItemClick(int position) {

    }
}
