package com.example.yara.weatherapp.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Yara on 18-Jan-19.
 */

@Dao
public interface WeatherDao {
    @Query(" SELECT * FROM Weather")
    LiveData<List<WeatherEntry>> loadAllData();

    @Insert
    void  insertMovie (WeatherEntry movieEntry);


    @Query("DELETE FROM Weather")
    public void delete();
}
