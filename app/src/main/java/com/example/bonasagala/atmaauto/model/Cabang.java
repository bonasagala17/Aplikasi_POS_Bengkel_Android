package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Cabang {
    @SerializedName("ID_CABANG")
    private int id;
    @SerializedName("NAMA_CABANG")
    private String nama_cabang;
    @SerializedName("ALAMAT_CABANG")
    private String alamat_cabang;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_cabang()
    {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang)
    {
        this.nama_cabang = nama_cabang;
    }

    public String getAlamat_cabang() {
        return alamat_cabang;
    }

    public void setAlamat_cabang(String alamat_cabang)
    {
        this.alamat_cabang = alamat_cabang;
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
