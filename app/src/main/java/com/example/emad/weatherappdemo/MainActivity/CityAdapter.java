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
import com.example.emad.weatherappdemo.R;
import com.example.emad.weatherappdemo.UnitConverter;
import com.example.emad.weatherappdemo.WeatherIconFactory;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VHolder> {

    ArrayList<CityWeather> mCitesWeather;
    ItemListener mItemListener;

    public CityAdapter(ArrayList<CityWeather> citesWeather, ItemListener itemListener) {
        this.mCitesWeather = citesWeather;
        this.mItemListener = itemListener;
    }

    public void updateDataSet(ArrayList<CityWeather> newDataSet) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(mCitesWeather, newDataSet));
        result.dispatchUpdatesTo(this);
    }

    public void setItemListener(ItemListener listener) {
        this.mItemListener = listener;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_temp, parent, false);
        return new VHolder(view, mItemListener);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        holder.temp.setText(String.format("%02d", (int)mCitesWeather.get(position).getMain().getTemp()));
        holder.tempMin.setText(String.format("%02d", (int)mCitesWeather.get(position).getMain().getTempMin()));
        holder.tempMax.setText(String.format("%02d", (int)mCitesWeather.get(position).getMain().getTempMax()));
        holder.cityName.setText(mCitesWeather.get(position).getName());
        int v = UnitConverter.convertMeterToKilo(mCitesWeather.get(position).getVisibility());
        holder.visibility.setText(String.format("%02d KM", v));
        holder.humidity.setText(String.format("%02d %%",mCitesWeather.get(position).getMain().getHumidity()));
        holder.weatherDescription.setText(mCitesWeather.get(position).getWeather().get(0).getDescription());
        holder.weatherIcon.setImageResource(WeatherIconFactory.getIcon(mCitesWeather.get(position).getWeather().get(0).getIcon()));
    }

    @Override
    public int getItemCount() {
        return mCitesWeather.size();
    }

    static class VHolder extends RecyclerView.ViewHolder {

        ImageView weatherIcon;
        TextView weatherDescription;
        TextView visibility;
        TextView temp;
        TextView tempMin;
        TextView tempMax;
        TextView humidity;
        TextView cityName;

        VHolder(View itemView, final ItemListener listener) {
            super(itemView);
            humidity = itemView.findViewById(R.id.humidity_view_text);
            tempMax = itemView.findViewById(R.id.temperature_view_text_max_temp);
            tempMin = itemView.findViewById(R.id.temperature_view_text_min_temp);
            temp = itemView.findViewById(R.id.temperature_view_text_temp);
            visibility = itemView.findViewById(R.id.visibility_view_text);
            weatherDescription = itemView.findViewById(R.id.main_weather_state_text);
            weatherIcon = itemView.findViewById(R.id.main_weather_state_img);
            cityName = itemView.findViewById(R.id.item_city_name);

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

        ArrayList<CityWeather> mOldSet;
        ArrayList<CityWeather> mNewSet;

        public DiffCallback(ArrayList<CityWeather> oldSet, ArrayList<CityWeather> newSet) {
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
