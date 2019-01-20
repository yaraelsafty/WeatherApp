package com.example.yara.weatherapp.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Yara on 18-Jan-19.
 */

public class WeatherRepository {
    private WeatherDao weatherDao;
    private LiveData<List<WeatherEntry>> mAllMovies;


    public WeatherRepository(Application application) {
        AppDatabase db = AppDatabase.getsInstance(application);
        weatherDao = db.weatherDao();
        mAllMovies = weatherDao.loadAllData();
    }

    public LiveData<List<WeatherEntry>> getAllData() {
        return mAllMovies;
    }


    public void insert(WeatherEntry weatherEntry) {
        new insertAsyncTask(weatherDao).execute(weatherEntry);
    }

    public void delete()  {
        new deleteAllAsyncTask(weatherDao).execute();
    }



    private static class insertAsyncTask extends AsyncTask<WeatherEntry, Void, Void> {

        private WeatherDao mAsyncTaskDao;

        insertAsyncTask(WeatherDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final WeatherEntry... params) {
            mAsyncTaskDao.insertMovie(params[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WeatherDao mAsyncTaskDao;

        deleteAllAsyncTask(WeatherDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.delete();
            return null;
        }
    }
}
