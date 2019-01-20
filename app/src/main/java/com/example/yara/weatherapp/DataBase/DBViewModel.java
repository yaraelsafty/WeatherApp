package com.example.yara.weatherapp.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Yara on 19-Jan-19.
 */

public class DBViewModel extends AndroidViewModel {

    LiveData<List<WeatherEntry>> list;
    WeatherEntry Entry;
    private WeatherRepository mRepository;
    String TAG = this.getClass().getSimpleName();
    public DBViewModel(@NonNull Application application) {
        super(application);

        mRepository = new WeatherRepository(application);
        list = mRepository.getAllData();

    }
    public void insert(WeatherEntry weatherEntry) { mRepository.insert(weatherEntry); }

    public void deleteAll() {mRepository.delete();}
}
