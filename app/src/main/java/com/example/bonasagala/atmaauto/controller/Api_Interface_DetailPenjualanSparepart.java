package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Detail_PenjualanSparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_DetailPenjualanSparepart {
    @POST("get_detailPenjualanSparepart.php")
    Call<List<Detail_PenjualanSparepart>> getDetailPenjualanSparepart();

    @FormUrlEncoded
    @POST("create_detailPenjualanSparepart.php")
    Call<Detail_PenjualanSparepart> createDetailPenjualanSparepart(
            @Field("key") String key,
            @Field("ID_SPAREPART") String id_sparepart,
            @Field("ID_TRANSAKSI_PEMBAYARAN") String id_transaksi_pembayaran,
            @Field("ID_MONTIR") String id_montir,
            @Field("JUMLAH") String jumlah,
            @Field("SUBTOTAL") String subtotal
    );

    @FormUrlEncoded
    @POST("update_detailPenjualanSparepart.php")
    Call<Detail_PenjualanSparepart> updateDetailPenjualanSparepart(
            @Field("key") String key,
            @Field("ID_DETAIL_TRANSAKSI_SPAREPART") int id_detail_transaksi_sparepart,
            @Field("ID_SPAREPART") String id_sparepart,
            @Field("ID_TRANSAKSI_PEMBAYARAN") String id_transaksi_pembayaran,
            @Field("ID_MONTIR") String id_montir,
            @Field("JUMLAH") String jumlah,
            @Field("SUBTOTAL") String subtotal
    );

    /*@FormUrlEncoded
    @POST("hapus_detailPenjualanSparepart.php")
    Call<Detail_PenjualanSparepart> hapusDetailPenjualanSparepart(
            @Field("key") String key,
            @Field("ID_DETAIL_TRANSAKSI_SPAREPART") int id_detail_transaksi_sparepart
    );*/
}
