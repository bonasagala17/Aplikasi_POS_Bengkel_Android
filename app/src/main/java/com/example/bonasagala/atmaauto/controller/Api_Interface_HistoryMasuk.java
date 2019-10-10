package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.History_Masuk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_Interface_HistoryMasuk {

    @POST("get_historiMasuk.php")
    Call<List<History_Masuk>> getHistoryMasuk();
}
