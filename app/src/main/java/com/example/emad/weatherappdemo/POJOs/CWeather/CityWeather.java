
package com.example.emad.weatherappdemo.POJOs.CWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type City weather.
 */
public class CityWeather {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private int cod;

    /**
     * Gets coord.
     *
     * @return the coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Sets coord.
     *
     * @param coord the coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * Gets weather.
     *
     * @return the weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * Sets weather.
     *
     * @param weather the weather
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * Gets base.
     *
     * @return the base
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets base.
     *
     * @param base the base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Gets main.
     *
     * @return the main
     */
    public Main getMain() {
        return main;
    }

    /**
     * Sets main.
     *
     * @param main the main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Gets visibility.
     *
     * @return the visibility
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Sets visibility.
     *
     * @param visibility the visibility
     */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    /**
     * Gets wind.
     *
     * @return the wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets wind.
     *
     * @param wind the wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * Gets clouds.
     *
     * @return the clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Sets clouds.
     *
     * @param clouds the clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

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
     * Gets sys.
     *
     * @return the sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * Sets sys.
     *
     * @param sys the sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets cod.
     *
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * Sets cod.
     *
     * @param cod the cod
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityWeather that = (CityWeather) o;

        if (visibility != that.visibility) return false;
        if (dt != that.dt) return false;
        if (id != that.id) return false;
        if (cod != that.cod) return false;
        if (coord != null ? !coord.equals(that.coord) : that.coord != null) return false;
        if (weather != null ? !weather.equals(that.weather) : that.weather != null) return false;
        if (base != null ? !base.equals(that.base) : that.base != null) return false;
        if (main != null ? !main.equals(that.main) : that.main != null) return false;
        if (wind != null ? !wind.equals(that.wind) : that.wind != null) return false;
        if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null) return false;
        if (sys != null ? !sys.equals(that.sys) : that.sys != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = coord != null ? coord.hashCode() : 0;
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (base != null ? base.hashCode() : 0);
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + visibility;
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        result = 31 * result + dt;
        result = 31 * result + (sys != null ? sys.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + cod;
        return result;
    }

}
