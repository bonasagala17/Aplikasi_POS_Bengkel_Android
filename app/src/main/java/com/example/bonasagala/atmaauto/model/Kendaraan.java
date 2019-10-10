package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Kendaraan {

    @SerializedName("ID_KENDARAAN")
    private int id_kendaraan;
    @SerializedName("MERK_KENDARAAN")
    private String merk_kendaraan;
    @SerializedName("TIPE_KENDARAAN")
    private String tipe_kendaraan;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getId_kendaraan() {
        return id_kendaraan;
    }

    public void setId_kendaraan(int id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public String getMerk_kendaraan() {
        return merk_kendaraan;
    }

    public void setMerk_kendaraan(String merk_kendaraan) {
        this.merk_kendaraan = merk_kendaraan;
    }

    public String getTipe_kendaraan() {
        return tipe_kendaraan;
    }

    public void setTipe_kendaraan(String tipe_kendaraan) {
        this.tipe_kendaraan = tipe_kendaraan;
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
