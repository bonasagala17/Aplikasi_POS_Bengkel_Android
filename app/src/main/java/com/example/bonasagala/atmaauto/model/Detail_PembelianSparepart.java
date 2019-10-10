package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Detail_PembelianSparepart {

    @SerializedName("ID_DETAIL_PEMBELIAN")
    private int id_detail_pembelian;
    @SerializedName("ID_PEMBELIAN")
    private String id_pembelian;
    @SerializedName("ID_SPAREPART")
    private String id_sparepart;
    @SerializedName("HARGABELI")
    private String hargabeli;
    @SerializedName("JUMLAH")
    private String jumlah;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getId_detail_pembelian() {
        return id_detail_pembelian;
    }

    public void setId_detail_pembelian(int id_detail_pembelian) {
        this.id_detail_pembelian = id_detail_pembelian;
    }

    public String getId_pembelian() {
        return id_pembelian;
    }

    public void setId_pembelian(String id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

    public String getId_sparepart() {
        return id_sparepart;
    }

    public void setId_sparepart(String id_sparepart) {
        this.id_sparepart = id_sparepart;
    }

    public String getHargabeli() {
        return hargabeli;
    }

    public void setHargabeli(String hargabeli) {
        this.hargabeli = hargabeli;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
