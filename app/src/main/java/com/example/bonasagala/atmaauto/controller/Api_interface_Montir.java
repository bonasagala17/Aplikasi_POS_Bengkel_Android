package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Montir;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_interface_Montir {
    @POST("get_montir.php")
    Call<List<Montir>> getMontir();
}
