package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Penjualan_JasaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_PenjualanJasaService {

    @POST("get_penjualanJasaService.php")
    Call<List<Penjualan_JasaService>> get_penjualanJasaService(

            //@Field("ID_TRANSAKSI_PEMBAYARAN") int id_transaksi_pembayaran
    );


    @FormUrlEncoded
    @POST("create_penjualanJasaService.php")
    Call<Penjualan_JasaService> create_penjualanJasaService(
            @Field("key") String key,
            @Field("ID_JASASERVICE") String id_jasaservice,
            @Field("ID_TRANSAKSI_PEMBAYARAN") String id_transaksi_pembayaran,
            @Field("ID_KENDARAAN_CUSTOMER") String id_kendaraan_customer,
            @Field("ID_MONTIR") String id_montir,
            @Field("JUMLAH") String jumlah,
            @Field("SUBTOTAL") String subtotal
    );

    @FormUrlEncoded
    @POST("update_penjualanJasaService.php")
    Call<Penjualan_JasaService> updateDetailPenjualanJasaService(
            @Field("key") String key,
            @Field("ID_DETAIL_TRANSAKSI_JASASERVICE") int id_detail_transaksi_jasaservice,
            @Field("ID_JASASERVICE") String id_jasaservice,
            @Field("ID_TRANSAKSI_PEMBAYARAN") String id_transaksi_pembayaran,
            @Field("ID_MONTIR") String id_montir,
            @Field("JUMLAH") String jumlah,
            @Field("SUBTOTAL") String subtotal
    );
}
