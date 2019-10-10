package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Montir {

    @SerializedName("ID_MONTIR")
    private int id_montir;
    @SerializedName("ID_PEGAWAI")
    private String id_pegawai;
    @SerializedName("ID_KENDARAAN_CUSTOMER")
    private String id_kendaraan_customer;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getId_montir() {
        return id_montir;
    }

    public void setId_montir(int id_montir) {
        this.id_montir = id_montir;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getId_kendaraan_customer() {
        return id_kendaraan_customer;
    }

    public void setId_kendaraan_customer(String id_kendaraan_customer) {
        this.id_kendaraan_customer = id_kendaraan_customer;
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
