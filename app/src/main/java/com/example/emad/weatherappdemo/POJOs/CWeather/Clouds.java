
package com.example.emad.weatherappdemo.POJOs.CWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Clouds.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    /**
     * Gets all.
     *
     * @return the all
     */
    public int getAll() {
        return all;
    }

    /**
     * Sets all.
     *
     * @param all the all
     */
    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return all == clouds.all;
    }

    @Override
    public int hashCode() {
        return all;
    }

}
