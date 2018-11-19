package com.example.emad.weatherappdemo;

public class WeatherIconFactory {

    public static int getIcon(String id) {
        if (id.contains("01")) {
            return R.drawable.ic_w_clear;
        } else if (id.contains("02")) {
            return R.drawable.ic_w_light_cloud;
        }else if (id.contains("03")) {
            return R.drawable.ic_w_heavy_cloud;
        }else if (id.contains("04")) {
            return R.drawable.ic_w_heavy_cloud;
        }else if (id.contains("09")) {
            return R.drawable.ic_w_heavy_rain;
        }else if (id.contains("10")) {
            return R.drawable.ic_w_light_rain;
        }else if (id.contains("11")) {
            return R.drawable.ic_w_thunder_storm;
        }else if (id.contains("13")) {
            return R.drawable.ic_w_snow;
        }else if (id.contains("50")) {
            return R.drawable.ic_w_sleet;
        }else {
            return 0;
        }
    }
}
