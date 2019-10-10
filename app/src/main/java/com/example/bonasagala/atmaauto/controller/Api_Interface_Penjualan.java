package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Penjualan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_Penjualan {

    @POST("get_penjualan_sparepart.php")
    Call<List<Penjualan>> getPenjulanSparepart();

    @FormUrlEncoded
    @POST("create_penjualan_sparepart.php")
    Call<Penjualan> createPenjulanSparepart(
            @Field("key") String key,
            @Field("ID_KONSUMEN") String id_konsumen,
            @Field("ID_CABANG") String id_cabang,
            @Field("NO_TRANSAKSI") String no_transaksi,
            @Field("TANGGAL_TRANSAKSI") String tanggal_transaksi,
            @Field("DISKON") String diskon,
            @Field("JENIS_PENJUALAN") String jenis_penjualan,
            @Field("TOTAL_TRANSAKSI") String total_transaksi,
            @Field("STATUS_PEMBAYARAN") String status_pembayaran
    );

    @FormUrlEncoded
    @POST("update_penjualan_sparepart.php")
    Call<Penjualan> updatePenjulanSparepart(
            @Field("key") String key,
            @Field("ID_TRANSAKSI_PEMBAYARAN") int id_transaksi_pembayaran,
            @Field("ID_KONSUMEN") String id_konsumen,
            @Field("ID_CABANG") String id_cabang,
            @Field("NO_TRANSAKSI") String no_transaksi,
            @Field("TANGGAL_TRANSAKSI") String tanggal_transaksi,
            @Field("DISKON") String diskon,
            @Field("TOTAL_TRANSAKSI") String total_transaksi,
            @Field("STATUS_PEMBAYARAN") String status_pembayaran
    );

    /*@FormUrlEncoded
    @POST("hapus_cabang.php")
    Call<Cabang> hapusCabang(
            @Field("key") String key,
            @Field("ID_CABANG") int id
    );*/
}
