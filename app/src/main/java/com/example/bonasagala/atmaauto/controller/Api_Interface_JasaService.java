package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.JasaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_Interface_JasaService {

    @POST("get_jasaservice.php")
    Call<List<JasaService>> getJasaService();
}
