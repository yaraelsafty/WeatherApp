package com.example.yara.weatherapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.yara.weatherapp.Model.Result;
import com.example.yara.weatherapp.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yara on 17-Jan-19.
 */

public class WeatherViewModel extends ViewModel {
    public String id;
    private MutableLiveData<Result> WeatherList;



    //we will call this method to get the data
    public LiveData<Result> getWeather() {
        //if the list is null
        if (WeatherList == null) {
            WeatherList = new MutableLiveData<Result>();
            //we will load it asynchronously from server in this method
            loadWeather(id);
        }

        //finally we will return the list
        return WeatherList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadWeather(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Result> call = api.getWeather(id, "10", ApiInterface.ApiKey);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                WeatherList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("error", t.getMessage());
            }

        });
    }

}
