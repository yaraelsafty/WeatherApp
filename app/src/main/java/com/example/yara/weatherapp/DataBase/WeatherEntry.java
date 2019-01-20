package com.example.yara.weatherapp.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yara on 18-Jan-19.
 */
@Entity(tableName = "Weather")
public class WeatherEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String dtTxt;
    private String icon;
    private Double tempMin;
    private Double tempMax;

    public WeatherEntry(int id, String name, String dtTxt, String icon, Double tempMin, Double tempMax) {
        this.id = id;
        this.name = name;
        this.dtTxt = dtTxt;
        this.icon = icon;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }
@Ignore
    public WeatherEntry(String name, String dtTxt, String icon, Double tempMin, Double tempMax) {
        this.name = name;
        this.dtTxt = dtTxt;
        this.icon = icon;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
