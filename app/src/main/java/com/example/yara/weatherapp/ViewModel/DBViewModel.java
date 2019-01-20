package com.example.yara.weatherapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.yara.weatherapp.DataBase.WeatherEntry;
import com.example.yara.weatherapp.DataBase.WeatherRepository;

import java.util.List;

/**
 * Created by Yara on 19-Jan-19.
 */

public class DBViewModel extends AndroidViewModel {
    String TAG = this.getClass().getSimpleName();

    private LiveData<List<WeatherEntry>> list;
    WeatherEntry Entry;
    private WeatherRepository mRepository;

    public DBViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WeatherRepository(application);
        list = mRepository.getAllData();
    }
    public LiveData<List<WeatherEntry>> getAllMovies() {

        return list;
    }

    public void insert(WeatherEntry movieEntry) {

        mRepository.insert(movieEntry);
    }

    public void delete(){
        mRepository.delete();
    }
}
