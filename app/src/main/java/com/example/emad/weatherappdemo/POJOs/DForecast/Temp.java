
package com.example.emad.weatherappdemo.POJOs.DForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {

    @SerializedName("day")
    @Expose
    private float day;
    @SerializedName("min")
    @Expose
    private float min;
    @SerializedName("max")
    @Expose
    private float max;
    @SerializedName("night")
    @Expose
    private float night;
    @SerializedName("eve")
    @Expose
    private float eve;
    @SerializedName("morn")
    @Expose
    private float morn;

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEve() {
        return eve;
    }

    public void setEve(float eve) {
        this.eve = eve;
    }

    public float getMorn() {
        return morn;
    }

    public void setMorn(float morn) {
        this.morn = morn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temp temp = (Temp) o;

        if (Float.compare(temp.day, day) != 0) return false;
        if (Float.compare(temp.min, min) != 0) return false;
        if (Float.compare(temp.max, max) != 0) return false;
        if (Float.compare(temp.night, night) != 0) return false;
        if (Float.compare(temp.eve, eve) != 0) return false;
        return Float.compare(temp.morn, morn) == 0;
    }

    @Override
    public int hashCode() {
        int result = (day != +0.0f ? Float.floatToIntBits(day) : 0);
        result = 31 * result + (min != +0.0f ? Float.floatToIntBits(min) : 0);
        result = 31 * result + (max != +0.0f ? Float.floatToIntBits(max) : 0);
        result = 31 * result + (night != +0.0f ? Float.floatToIntBits(night) : 0);
        result = 31 * result + (eve != +0.0f ? Float.floatToIntBits(eve) : 0);
        result = 31 * result + (morn != +0.0f ? Float.floatToIntBits(morn) : 0);
        return result;
    }
}
