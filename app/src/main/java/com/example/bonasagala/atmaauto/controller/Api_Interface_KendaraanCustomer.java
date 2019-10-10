package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Kendaraan_Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api_Interface_KendaraanCustomer {
    @POST("get_kendaraanCustomer.php")
    Call<List<Kendaraan_Customer>> getKendaraanCustomer();
}
