package com.example.emad.weatherappdemo.MainActivity;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.POJOs.DForecast.Forecast;
import com.example.emad.weatherappdemo.R;
import com.example.emad.weatherappdemo.UnitConverter;
import com.example.emad.weatherappdemo.WeatherIconFactory;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.VHolder> {

    List<Forecast> mForecast;
    ItemListener mItemListener;

    public ForecastAdapter(List<Forecast> forecast, ItemListener itemListener) {
        this.mForecast = forecast;
        this.mItemListener = itemListener;
    }

    public void updateDataSet(List<Forecast> newDataSet) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(mForecast, newDataSet));
        result.dispatchUpdatesTo(this);
    }

    public void setItemListener(ItemListener listener) {
        this.mItemListener = listener;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_temp, parent, false);
        return new VHolder(view, mItemListener);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        Forecast forecast = mForecast.get(position+1);
        holder.temp.setText(String.format("%02d", (int)forecast.getTemp().getEve()));
        holder.tempMin.setText(String.format("%02d", (int)forecast.getTemp().getMin()));
        holder.tempMax.setText(String.format("%02d", (int)forecast.getTemp().getMax()));
        holder.date.setText(UnitConverter.convertUnixTimeToDate(forecast.getDt()));
        holder.humidity.setText(String.format("%02d %%",forecast.getHumidity()));
        holder.weatherDescription.setText(forecast.getWeather().get(0).getDescription());
        holder.weatherIcon.setImageResource(WeatherIconFactory.getIcon(forecast.getWeather().get(0).getIcon()));

    }

    @Override
    public int getItemCount() {
        return mForecast.size()-1;
    }

    static class VHolder extends RecyclerView.ViewHolder {

        ImageView weatherIcon;
        TextView weatherDescription;
        TextView temp;
        TextView tempMin;
        TextView tempMax;
        TextView humidity;
        TextView date;

        VHolder(View itemView, final ItemListener listener) {
            super(itemView);
            humidity = itemView.findViewById(R.id.humidity_view_text);
            tempMax = itemView.findViewById(R.id.temperature_view_text_max_temp);
            tempMin = itemView.findViewById(R.id.temperature_view_text_min_temp);
            temp = itemView.findViewById(R.id.temperature_view_text_temp);
            weatherDescription = itemView.findViewById(R.id.main_weather_state_text);
            weatherIcon = itemView.findViewById(R.id.main_weather_state_img);
            date = itemView.findViewById(R.id.item_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(getLayoutPosition());
                }
            });

        }
    }

    public interface ItemListener {
        void onItemClick(int position);
    }

    private static class DiffCallback extends DiffUtil.Callback {

        List<Forecast> mOldSet;
        List<Forecast> mNewSet;

        public DiffCallback(List<Forecast> oldSet, List<Forecast> newSet) {
            this.mOldSet = oldSet;
            this.mNewSet = newSet;
        }

        @Override
        public int getOldListSize() {
            return mOldSet.size();
        }

        @Override
        public int getNewListSize() {
            return mNewSet.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewSet.get(newItemPosition) == mOldSet.get(oldItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewSet.get(newItemPosition).equals(mOldSet.get(oldItemPosition));
        }
    }

}
