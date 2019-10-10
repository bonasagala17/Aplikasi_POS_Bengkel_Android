package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Pembelian_Sparepart {

    @SerializedName("ID_PEMBELIAN")
    private int id_pembelian;
    @SerializedName("ID_SUPPLIER")
    private String id_supplier;
    @SerializedName("TANGGAL_PEMBELIAN")
    private String tanggal_pembelian;
    @SerializedName("TOTAL_PEMBAYARAN")
    private String total_pembayaran;
    @SerializedName("STATUS")
    private String status;
    @SerializedName("valuePembelian")
    private String valuePembelian;
    @SerializedName("messagePembelian")
    private String massagePembelian;

    public int getId_pembelian() {
        return id_pembelian;
    }

    public void setId_pembelian(int id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

    public String getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(String id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getTanggal_pembelian() {
        return tanggal_pembelian;
    }

    public void setTanggal_pembelian(String tanggal_pembelian) {
        this.tanggal_pembelian = tanggal_pembelian;
    }

    public String getTotal_pembayaran() {
        return total_pembayaran;
    }

    public void setTotal_pembayaran(String total_pembayaran) {
        this.total_pembayaran = total_pembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValuePembelian() {
        return valuePembelian;
    }

    public void setValuePembelian(String valuePembelian) {
        this.valuePembelian = valuePembelian;
    }

    public String getMassagePembelian() {
        return massagePembelian;
    }

    public void setMassagePembelian(String massagePembelian) {
        this.massagePembelian = massagePembelian;
    }
}
