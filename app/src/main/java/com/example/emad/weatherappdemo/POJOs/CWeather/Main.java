
package com.example.emad.weatherappdemo.POJOs.CWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Main.
 */
public class Main {

    @SerializedName("temp")
    @Expose
    private float temp;
    @SerializedName("pressure")
    @Expose
    private float pressure;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("temp_min")
    @Expose
    private float tempMin;
    @SerializedName("temp_max")
    @Expose
    private float tempMax;

    /**
     * Gets temp.
     *
     * @return the temp
     */
    public float getTemp() {
        return temp;
    }

    /**
     * Sets temp.
     *
     * @param temp the temp
     */
    public void setTemp(float temp) {
        this.temp = temp;
    }

    /**
     * Gets pressure.
     *
     * @return the pressure
     */
    public float getPressure() {
        return pressure;
    }

    /**
     * Sets pressure.
     *
     * @param pressure the pressure
     */
    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets humidity.
     *
     * @return the humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity.
     *
     * @param humidity the humidity
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets temp min.
     *
     * @return the temp min
     */
    public float getTempMin() {
        return tempMin;
    }

    /**
     * Sets temp min.
     *
     * @param tempMin the temp min
     */
    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Gets temp max.
     *
     * @return the temp max
     */
    public float getTempMax() {
        return tempMax;
    }

    /**
     * Sets temp max.
     *
     * @param tempMax the temp max
     */
    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (Float.compare(main.temp, temp) != 0) return false;
        if (Float.compare(main.pressure, pressure) != 0) return false;
        if (humidity != main.humidity) return false;
        if (Float.compare(main.tempMin, tempMin) != 0) return false;
        return Float.compare(main.tempMax, tempMax) == 0;
    }

    @Override
    public int hashCode() {
        int result = (temp != +0.0f ? Float.floatToIntBits(temp) : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + humidity;
        result = 31 * result + (tempMin != +0.0f ? Float.floatToIntBits(tempMin) : 0);
        result = 31 * result + (tempMax != +0.0f ? Float.floatToIntBits(tempMax) : 0);
        return result;
    }
}
