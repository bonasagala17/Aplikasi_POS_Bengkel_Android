package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Pegawai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_Interface_Pegawai {

    @POST("get_pegawai.php")
    Call<List<Pegawai>> getPegawai();
}