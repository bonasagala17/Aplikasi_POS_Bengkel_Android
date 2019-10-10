package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_Sparepart {
    @POST("get_sparepart.php")
    Call<List<Sparepart>> getSparepart();

    @FormUrlEncoded
    @POST("create_sparepart.php")
    Call<Sparepart> createSparepart(
            @Field("key") String key,
            @Field("NAMA_SPAREPART") String nama_sparepart,
            @Field("MERK_SPAREPART") String merk_sparepart,
            @Field("TIPE_SPAREPART") String tipe_sparepart,
            @Field("HARGABELI_SPAREPART") int hargabeli_sparepart,
            @Field("HARGAJUAL_SPAREPART") int hargajual_sparepart,
            @Field("STOK_SPAREPART") int stok_sparepart,
            @Field("STOKOPTIMAL_SPAREPART") int stokoptimal_sparepart,
            @Field("GAMBAR_SPAREPART") String gambar_sparepart,
            @Field("LETAK_SPAREPART") String letak_sparepart
    );

    @FormUrlEncoded
    @POST("update_sparepart.php")
    Call<Sparepart> updateSparepart(
            @Field("key") String key,
            @Field("ID_SPAREPART") int id_sparepart,
            @Field("NAMA_SPAREPART") String nama_sparepart,
            @Field("MERK_SPAREPART") String merk_sparepart,
            @Field("TIPE_SPAREPART") String tipe_sparepart,
            @Field("HARGABELI_SPAREPART") int hargabeli_sparepart,
            @Field("HARGAJUAL_SPAREPART") int hargajual_sparepart,
            @Field("STOK_SPAREPART") int stok_sparepart,
            @Field("STOKOPTIMAL_SPAREPART") int stokoptimal_sparepart,
            @Field("GAMBAR_SPAREPART") String gambar_sparepart,
            @Field("LETAK_SPAREPART") String letak_sparepart
    );

    @FormUrlEncoded
    @POST("hapus_sparepart.php")
    Call<Sparepart> hapusSparepart(
            @Field("key") String key,
            @Field("ID_SPAREPART") int id_sparepart,
            @Field("GAMBAR_SPAREPART") String gambar_sparepart
    );
}
