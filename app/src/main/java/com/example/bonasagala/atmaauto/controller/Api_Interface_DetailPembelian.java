package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Detail_PembelianSparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_DetailPembelian {

    @POST("get_detailPembelian.php")
    Call<List<Detail_PembelianSparepart>> getDetailPembelian();

    @FormUrlEncoded
    @POST("create_detailPembelian.php")
    Call<Detail_PembelianSparepart> createDetailPembelian(
            @Field("key") String key,
            @Field("ID_PEMBELIAN") String id_pembelian,
            @Field("ID_SPAREPART") String id_sparepart,
            @Field("HARGABELI") String hargabeli,
            @Field("JUMLAH") String jumlah
    );

    @FormUrlEncoded
    @POST("update_detailPembelian.php")
    Call<Detail_PembelianSparepart> updateDetailPembelian(
            @Field("key") String key,
            @Field("ID_DETAIL_PEMBELIAN") int id_detail_pembelian,
            @Field("ID_PEMBELIAN") String id_pembelian,
            @Field("ID_SPAREPART") String id_sparepart,
            @Field("HARGABELI") String hargabeli,
            @Field("JUMLAH") String jumlah
    );

    @FormUrlEncoded
    @POST("hapus_detailPembelian.php")
    Call<Detail_PembelianSparepart> hapusDetailPembelian(
            @Field("key") String key,
            @Field("ID_DETAIL_PEMBELIAN") int id_detail_pembelian
    );
}
