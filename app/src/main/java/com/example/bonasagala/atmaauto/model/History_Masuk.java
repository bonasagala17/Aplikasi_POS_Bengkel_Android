package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class History_Masuk {
    @SerializedName("NAMA_SPAREPART")
    private String nama_sparepart;
    @SerializedName("TANGGAL_PEMBELIAN")
    private String tanggal_pembelian;

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getTanggal_pembelian() {
        return tanggal_pembelian;
    }

    public void setTanggal_pembelian(String tanggal_pembelian) {
        this.tanggal_pembelian = tanggal_pembelian;
    }
}
