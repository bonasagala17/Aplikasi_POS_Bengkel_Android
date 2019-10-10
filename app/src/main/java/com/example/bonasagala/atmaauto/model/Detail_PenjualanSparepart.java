package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Detail_PenjualanSparepart {

    @SerializedName("ID_DETAIL_TRANSAKSI_SPAREPART")
    private int id_detail_transaksi_sparepart;
    @SerializedName("ID_SPAREPART")
    private String id_sparepart;
    @SerializedName("ID_TRANSAKSI_PEMBAYARAN")
    private String id_transaksi_pembayaran;
    @SerializedName("ID_MONTIR")
    private String id_montir;
    @SerializedName("JUMLAH")
    private String jumlah;
    @SerializedName("SUBTOTAL")
    private String subtotal;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getId_detail_transaksi_sparepart() {
        return id_detail_transaksi_sparepart;
    }

    public void setId_detail_transaksi_sparepart(int id_detail_transaksi_sparepart) {
        this.id_detail_transaksi_sparepart = id_detail_transaksi_sparepart;
    }

    public String getId_sparepart() {
        return id_sparepart;
    }

    public void setId_sparepart(String id_sparepart) {
        this.id_sparepart = id_sparepart;
    }

    public String getId_transaksi_pembayaran() {
        return id_transaksi_pembayaran;
    }

    public void setId_transaksi_pembayaran(String id_transaksi_pembayaran) {
        this.id_transaksi_pembayaran = id_transaksi_pembayaran;
    }

    public String getId_montir() {
        return id_montir;
    }

    public void setId_montir(String id_montir) {
        this.id_montir = id_montir;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
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
