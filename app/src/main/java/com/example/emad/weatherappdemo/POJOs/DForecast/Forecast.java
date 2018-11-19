
package com.example.emad.weatherappdemo.POJOs.DForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Forecast.
 */
public class Forecast {

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private float pressure;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    private float speed;
    @SerializedName("deg")
    @Expose
    private int deg;
    @SerializedName("clouds")
    @Expose
    private int clouds;
    @SerializedName("rain")
    @Expose
    private float rain;

    /**
     * Gets dt.
     *
     * @return the dt
     */
    public int getDt() {
        return dt;
    }

    /**
     * Sets dt.
     *
     * @param dt the dt
     */
    public void setDt(int dt) {
        this.dt = dt;
    }

    /**
     * Gets temp.
     *
     * @return the temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     * Sets temp.
     *
     * @param temp the temp
     */
    public void setTemp(Temp temp) {
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
     * Gets weather.
     *
     * @return the weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * Sets weather.
     *
     * @param weather the weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

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
    public int getDeg() {
        return deg;
    }

    /**
     * Sets deg.
     *
     * @param deg the deg
     */
    public void setDeg(int deg) {
        this.deg = deg;
    }

    /**
     * Gets clouds.
     *
     * @return the clouds
     */
    public int getClouds() {
        return clouds;
    }

    /**
     * Sets clouds.
     *
     * @param clouds the clouds
     */
    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    /**
     * Gets rain.
     *
     * @return the rain
     */
    public float getRain() {
        return rain;
    }

    /**
     * Sets rain.
     *
     * @param rain the rain
     */
    public void setRain(float rain) {
        this.rain = rain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forecast forecast = (Forecast) o;

        if (dt != forecast.dt) return false;
        if (Float.compare(forecast.pressure, pressure) != 0) return false;
        if (humidity != forecast.humidity) return false;
        if (Float.compare(forecast.speed, speed) != 0) return false;
        if (deg != forecast.deg) return false;
        if (clouds != forecast.clouds) return false;
        if (Float.compare(forecast.rain, rain) != 0) return false;
        if (temp != null ? !temp.equals(forecast.temp) : forecast.temp != null) return false;
        return weather != null ? weather.equals(forecast.weather) : forecast.weather == null;
    }

    @Override
    public int hashCode() {
        int result = dt;
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + humidity;
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        result = 31 * result + deg;
        result = 31 * result + clouds;
        result = 31 * result + (rain != +0.0f ? Float.floatToIntBits(rain) : 0);
        return result;
    }
}
