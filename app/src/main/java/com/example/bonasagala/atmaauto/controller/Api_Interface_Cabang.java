package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Cabang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Api_Interface_Cabang {

    @POST("get_cabang.php")
    Call<List<Cabang>> getCabang();

    @FormUrlEncoded
    @POST("create_cabang.php")
    Call<Cabang> createCabang(
            @Field("key") String key,
            @Field("NAMA_CABANG") String nama_cabang,
            @Field("ALAMAT_CABANG") String alamat_cabang
    );

    @FormUrlEncoded
    @POST("update_cabang.php")
    Call<Cabang> updateCabang(
            @Field("key") String key,
            @Field("ID_CABANG") int id,
            @Field("NAMA_CABANG") String nama_cabang,
            @Field("ALAMAT_CABANG") String alamat_cabang
    );

    @FormUrlEncoded
    @POST("hapus_cabang.php")
    Call<Cabang> hapusCabang(
            @Field("key") String key,
            @Field("ID_CABANG") int id
    );
}
