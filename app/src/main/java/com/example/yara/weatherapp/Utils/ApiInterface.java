package com.example.yara.weatherapp.Utils;

import com.example.yara.weatherapp.Model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Yara on 17-Jan-19.
 */

public interface ApiInterface {

    String BASE_URL = "http://api.openweathermap.org/";
    String ApiKey="996689e49ce7c74e35f871039e06e974";

    @GET("/data/2.5/forecast")
    Call<Result> getWeather(@Query("id")String id,@Query("cnt") String cnt,@Query("APPID")String appId);
}
