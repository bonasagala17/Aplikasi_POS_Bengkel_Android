package com.example.bonasagala.atmaauto.controller;

import com.example.bonasagala.atmaauto.model.Supplier;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface_Supplier {
    @POST("get_supplier.php")
    Call<List<Supplier>> getSupplier();

    @FormUrlEncoded
    @POST("create_supplier.php")
    Call<Supplier> createSupplier(
            @Field("key") String key,
            @Field("NAMA_SUPPLIER") String nama_supplier,
            @Field("ALAMAT_SUPPLIER") String alamat_supplier,
            @Field("TELP_SUPPLIER") String telp_supplier,
            @Field("NAMA_SALES") String nama_sales,
            @Field("TELP_SALES") String telp_sales
    );

    @FormUrlEncoded
    @POST("update_supplier.php")
    Call<Supplier> updateSupplier(
            @Field("key") String key,
            @Field("ID_SUPPLIER") int id_supplier,
            @Field("NAMA_SUPPLIER") String nama_supplier,
            @Field("ALAMAT_SUPPLIER") String alamat_supplier,
            @Field("TELP_SUPPLIER") String telp_supplier,
            @Field("NAMA_SALES") String nama_sales,
            @Field("TELP_SALES") String telp_sales
    );

    @FormUrlEncoded
    @POST("hapus_supplier.php")
    Call<Supplier> hapusSupplier(
            @Field("key") String key,
            @Field("ID_SUPPLIER") int id_supplier
    );
}
