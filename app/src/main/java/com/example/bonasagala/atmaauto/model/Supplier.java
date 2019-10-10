package com.example.bonasagala.atmaauto.model;


import com.google.gson.annotations.SerializedName;

public class Supplier {
    @SerializedName("ID_SUPPLIER")
    private int id_supplier;
    @SerializedName("NAMA_SUPPLIER")
    private String nama_supplier;
    @SerializedName("ALAMAT_SUPPLIER")
    private String alamat_supplier;
    @SerializedName("TELP_SUPPLIER")
    private String telp_supplier;
    @SerializedName("NAMA_SALES")
    private String nama_sales;
    @SerializedName("TELP_SALES")
    private String telp_sales;
    @SerializedName("valueSupplier")
    private String valueSupplier;
    @SerializedName("messageSupplier")
    private String massageSupplier;

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public String getAlamat_supplier() {
        return alamat_supplier;
    }

    public void setAlamat_supplier(String alamat_supplier) {
        this.alamat_supplier = alamat_supplier;
    }

    public String getTelp_supplier() {
        return telp_supplier;
    }

    public void setTelp_supplier(String telp_supplier) {
        this.telp_supplier = telp_supplier;
    }

    public String getNama_sales() {
        return nama_sales;
    }

    public void setNama_sales(String nama_sales) {
        this.nama_sales = nama_sales;
    }

    public String getTelp_sales() {
        return telp_sales;
    }

    public void setTelp_sales(String telp_sales) {
        this.telp_sales = telp_sales;
    }

    public String getValueSupplier() {
        return valueSupplier;
    }

    public void setValueSupplier(String valueSupplier) {
        this.valueSupplier = valueSupplier;
    }

    public String getMassageSupplier() {
        return massageSupplier;
    }

    public void setMassageSupplier(String massageSupplier) {
        this.massageSupplier = massageSupplier;
    }
}
