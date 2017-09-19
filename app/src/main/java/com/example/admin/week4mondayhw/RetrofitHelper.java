package com.example.admin.week4mondayhw;



import com.example.admin.week4mondayhw.model.openweatherapp.Response;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 9/18/2017.
 */

public class RetrofitHelper {
    public static final String BASE_URL = "http://api.openweathermap.org/";

    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    //create a static method to use the interface verbs
    public static Call<Response> createCallWeather(String forecast, String APPID){
        Retrofit retrofit = create();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService.getCityForecast(forecast, APPID);
    }

    //create an interface to have all the paths and verbs for the REST api to use
    interface ApiService {
        @GET("data/2.5/forecast")// id={id}APPID={key}")//{user} or {abc} will make it {abc} dynamically
        Call<Response> getCityForecast(@Query("q") String forecast, @Query("APPID") String APPID);
    }

}

