
package com.example.emad.weatherappdemo.POJOs.DForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    @Expose
    private float lon;
    @SerializedName("lat")
    @Expose
    private float lat;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (Float.compare(coord.lon, lon) != 0) return false;
        return Float.compare(coord.lat, lat) == 0;
    }

    @Override
    public int hashCode() {
        int result = (lon != +0.0f ? Float.floatToIntBits(lon) : 0);
        result = 31 * result + (lat != +0.0f ? Float.floatToIntBits(lat) : 0);
        return result;
    }
}
