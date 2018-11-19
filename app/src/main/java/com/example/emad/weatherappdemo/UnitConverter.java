package com.example.emad.weatherappdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnitConverter {

    public static int convertMeterToKilo(int m) {
        return m / 1000;
    }

    public static String convertUnixTimeToDate(int t) {
        Date date = new java.util.Date(t * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.UK);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }

    public static int convertHpaToHg(float hpa) {
        return (int) (hpa / 33.863886666667);
    }
}
