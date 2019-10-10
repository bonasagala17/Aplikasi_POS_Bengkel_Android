package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Penjualan_JasaService {

    @SerializedName("ID_DETAIL_TRANSAKSI_JASASERVICE")
    private int id_detail_transaksi_jasaservice;
    @SerializedName("ID_JASASERVICE")
    private String id_jasaservice;
    @SerializedName("ID_TRANSAKSI_PEMBAYARAN")
    private String id_transaksi_pembayaran;
    @SerializedName("ID_KENDARAAN_CUSTOMER")
    private String id_kendaraan_customer;
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

    public int getId_detail_transaksi_jasaservice() {
        return id_detail_transaksi_jasaservice;
    }

    public void setId_detail_transaksi_jasaservice(int id_detail_transaksi_jasaservice) {
        this.id_detail_transaksi_jasaservice = id_detail_transaksi_jasaservice;
    }

    public String getId_jasaservice() {
        return id_jasaservice;
    }

    public void setId_jasaservice(String id_jasaservice) {
        this.id_jasaservice = id_jasaservice;
    }

    public String getId_transaksi_pembayaran() {
        return id_transaksi_pembayaran;
    }

    public void setId_transaksi_pembayaran(String id_transaksi_pembayaran) {
        this.id_transaksi_pembayaran = id_transaksi_pembayaran;
    }

    public String getId_kendaraan_customer() {
        return id_kendaraan_customer;
    }

    public void setId_kendaraan_customer(String id_kendaraan_customer) {
        this.id_kendaraan_customer = id_kendaraan_customer;
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
