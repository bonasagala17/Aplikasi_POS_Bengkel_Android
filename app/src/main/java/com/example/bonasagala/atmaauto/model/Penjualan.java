package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Penjualan {

    @SerializedName("ID_TRANSAKSI_PEMBAYARAN")
    private int id_transaksi_pembayaran;
    @SerializedName("ID_KONSUMEN")
    private String id_konsumen;
    @SerializedName("ID_CABANG")
    private String id_cabang;
    @SerializedName("NO_TRANSAKSI")
    private String no_transaksi;
    @SerializedName("TANGGAL_TRANSAKSI")
    private String tanggal_transaksi;
    @SerializedName("DISKON")
    private String diskon;
    @SerializedName("TOTAL_TRANSAKSI")
    private String total_transaksi;
    @SerializedName("STATUS_PEMBAYARAN")
    private String status_pembayaran;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getId_transaksi_pembayaran() {
        return id_transaksi_pembayaran;
    }

    public void setId_transaksi_pembayaran(int id_transaksi_pembayaran) {
        this.id_transaksi_pembayaran = id_transaksi_pembayaran;
    }

    public String getId_konsumen() {
        return id_konsumen;
    }

    public void setId_konsumen(String id_konsumen) {
        this.id_konsumen = id_konsumen;
    }

    public String getId_cabang() {
        return id_cabang;
    }

    public void setId_cabang(String id_cabang) {
        this.id_cabang = id_cabang;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getTotal_transaksi() {
        return total_transaksi;
    }

    public void setTotal_transaksi(String total_transaksi) {
        this.total_transaksi = total_transaksi;
    }

    public String getStatus_pembayaran() {
        return status_pembayaran;
    }

    public void setStatus_pembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
