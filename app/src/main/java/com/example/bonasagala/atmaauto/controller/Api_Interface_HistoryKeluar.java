package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.History_Keluar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_Interface_HistoryKeluar {
    @POST("get_historiKeluar.php")
    Call<List<History_Keluar>> getHistoryKeluar();
}
