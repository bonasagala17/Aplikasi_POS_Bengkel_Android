package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Masuk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_Masuk {
    @FormUrlEncoded
    @POST("login.php")
    Call<Masuk> loginRequest(
            @Field("USERNAME") String username,
            @Field("PASSWORD") String password
    );
}
