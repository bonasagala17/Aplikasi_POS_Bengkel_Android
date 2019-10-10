package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Kendaraan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_Interface_Kendaraan {

    @POST("get_kendaraan.php")
    Call<List<Kendaraan>> getKendaraan();
}
