package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Konsumen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Api_Interface_Konsumen {

    @POST("get_konsumen.php")
    Call<List<Konsumen>> getKonsumen();

    @FormUrlEncoded
    @POST("create_konsumen.php")
    Call<Konsumen> createKonsumen(
            @Field("key") String key,
            @Field("NAMA_KONSUMEN") String nama_konsumen,
            @Field("TELP_KONSUMEN") String telp_konsumen,
            @Field("ALAMAT_KONSUMEN") String alamat_konsumen
    );

    @FormUrlEncoded
    @POST("update_konsumen.php")
    Call<Konsumen> updateKonsumen(
            @Field("key") String key,
            @Field("ID_KONSUMEN") int id_konsumen,
            @Field("NAMA_KONSUMEN") String nama_konsumen,
            @Field("TELP_KONSUMEN") String telp_konsumen,
            @Field("ALAMAT_KONSUMEN") String alamat_konsumen
    );

    @FormUrlEncoded
    @POST("hapus_konsumen.php")
    Call<Konsumen> hapusKonsumen(
            @Field("key") String key,
            @Field("ID_KONSUMEN") int id_konsumen
    );
}
