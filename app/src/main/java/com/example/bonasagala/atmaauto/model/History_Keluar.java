package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class History_Keluar {
    @SerializedName("NAMA_SPAREPART")
    private String nama_sparepart;
    @SerializedName("TANGGAL_TRANSKASI")
    private String tanggal_transaksi;

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }
}
