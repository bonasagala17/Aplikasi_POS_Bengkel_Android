package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Pembelian_Sparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_Pembelian {

    @POST("get_pembelian.php")
    Call<List<Pembelian_Sparepart>> getPembelian();

    @FormUrlEncoded
    @POST("create_pembelian.php")
    Call<Pembelian_Sparepart> createPembelian(
            @Field("key") String key,
            @Field("ID_SUPPLIER") String id_supplier,
            @Field("TANGGAL_PEMBELIAN") String tanggal_pembelian,
            @Field("TOTAL_PEMBAYARAN") String total_pembayaran,
            @Field("STATUS") String status
    );

    @FormUrlEncoded
    @POST("update_pembelian.php")
    Call<Pembelian_Sparepart> updatePembelian(
            @Field("key") String key,
            @Field("ID_PEMBELIAN") int id_pembelian,
            @Field("ID_SUPPLIER") String id_supplier,
            @Field("TANGGAL_PEMBELIAN") String tanggal_pembelian,
            @Field("TOTAL_PEMBAYARAN") String total_pembayaran,
            @Field("STATUS") String status
    );

    @FormUrlEncoded
    @POST("hapus_pembelian.php")
    Call<Pembelian_Sparepart> hapusPembelian(
            @Field("key") String key,
            @Field("ID_PEMBELIAN") int id_pembelian
    );
}
