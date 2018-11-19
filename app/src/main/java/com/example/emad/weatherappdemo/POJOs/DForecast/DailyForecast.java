
package com.example.emad.weatherappdemo.POJOs.DForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyForecast {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private float message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<Forecast> list = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<Forecast> getList() {
        return list;
    }

    public void setList(java.util.List<Forecast> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyForecast that = (DailyForecast) o;

        if (Float.compare(that.message, message) != 0) return false;
        if (cnt != that.cnt) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (cod != null ? !cod.equals(that.cod) : that.cod != null) return false;
        return list != null ? list.equals(that.list) : that.list == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (cod != null ? cod.hashCode() : 0);
        result = 31 * result + (message != +0.0f ? Float.floatToIntBits(message) : 0);
        result = 31 * result + cnt;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }
}
