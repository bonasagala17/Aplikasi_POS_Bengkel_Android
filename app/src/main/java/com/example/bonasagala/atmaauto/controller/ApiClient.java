package com.example.bonasagala.atmaauto.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    /*private static final String BASE_URL = "http://10.53.6.154/restapi_8720/";*/
    private static final String BASE_URL = "http://192.168.19.140/restapi_8720/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if (retrofit==null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
