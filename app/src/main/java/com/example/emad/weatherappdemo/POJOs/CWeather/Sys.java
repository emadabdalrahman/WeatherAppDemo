
package com.example.emad.weatherappdemo.POJOs.CWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("message")
    @Expose
    private float message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private int sunrise;
    @SerializedName("sunset")
    @Expose
    private int sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (type != sys.type) return false;
        if (id != sys.id) return false;
        if (Float.compare(sys.message, message) != 0) return false;
        if (sunrise != sys.sunrise) return false;
        if (sunset != sys.sunset) return false;
        return country != null ? country.equals(sys.country) : sys.country == null;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + id;
        result = 31 * result + (message != +0.0f ? Float.floatToIntBits(message) : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + sunrise;
        result = 31 * result + sunset;
        return result;
    }
}
