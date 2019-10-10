package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.JS_SP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_JS_SP {

    @POST("get_penjualan_JS_SP.php")
    Call<List<JS_SP>> getPenjualanJS_SP();

    @FormUrlEncoded
    @POST("create_penjualanJS_SP.php")
    Call<JS_SP> createPenjualanJS_SP(
            @Field("key") String key,
            @Field("ID_JASASERVICE") String id_jasaservice,
            @Field("ID_SPAREPART") String id_sparepart,
            @Field("ID_TRANSAKSI_PEMBAYARAN") String id_transaksi_pembayaran,
            @Field("ID_MONTIR") String id_montir,
            @Field("JUMLAH") String jumlah,
            @Field("SUBTOTAL") String subtotal
    );

    /*@FormUrlEncoded
    @POST("update_penjualanJS_SP.php")
    Call<JS_SP> updatePenjualanJS_SP(
            @Field("key") String key,
            @Field("ID_JASASERVICE") String id_jasaservice,
            @Field("ID_SPAREPART") String id_sparepart,
            @Field("ID_TRANSAKSI_PEMBAYARAN") String id_transaksi_pembayaran,
            @Field("ID_MONTIR") String id_montir,
            @Field("JUMLAH") String jumlah,
            @Field("SUBTOTAL") String subtotal
    );*/
}
