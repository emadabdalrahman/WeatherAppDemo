
package com.example.emad.weatherappdemo.POJOs.CWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Wind.
 */
public class Wind {

    @SerializedName("speed")
    @Expose
    private float speed;
    @SerializedName("deg")
    @Expose
    private float deg;

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Gets deg.
     *
     * @return the deg
     */
    public float getDeg() {
        return deg;
    }

    /**
     * Sets deg.
     *
     * @param deg the deg
     */
    public void setDeg(float deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (Float.compare(wind.speed, speed) != 0) return false;
        return Float.compare(wind.deg, deg) == 0;
    }

    @Override
    public int hashCode() {
        int result = (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        result = 31 * result + (deg != +0.0f ? Float.floatToIntBits(deg) : 0);
        return result;
    }
}
