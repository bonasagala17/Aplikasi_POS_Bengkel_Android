package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Masuk;
import com.example.bonasagala.atmaauto.model.MasukCS;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_MasukCS {

    @FormUrlEncoded
    @POST("loginCS.php")
    Call<Masuk> loginRequestCS(
            @Field("USERNAME") String username,
            @Field("PASSWORD") String password
    );
}
