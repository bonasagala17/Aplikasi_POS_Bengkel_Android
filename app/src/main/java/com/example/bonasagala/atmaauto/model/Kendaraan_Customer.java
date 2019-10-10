package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Kendaraan_Customer {
    @SerializedName("ID_KENDARAAN_CUSTOMER")
    private int id_kendaraan_customer;
    @SerializedName("ID_KENDARAAN")
    private String id_kendaraan;
    @SerializedName("PLAT_NOMOR")
    private String plat_nomor;

    public int getId_kendaraan_customer() {
        return id_kendaraan_customer;
    }

    public void setId_kendaraan_customer(int id_kendaraan_customer) {
        this.id_kendaraan_customer = id_kendaraan_customer;
    }

    public String getId_kendaraan() {
        return id_kendaraan;
    }

    public void setId_kendaraan(String id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
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

    @SerializedName("value")

    private String value;
    @SerializedName("message")
    private String message;
}
