package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class JasaService {
    @SerializedName("ID_JASASERVICE")
    private int id_jasaservice;
    @SerializedName("NAMA_JASASERVICE")
    private String nama_jasaservcie;
    @SerializedName("HARGA_JASASERVICE")
    private String harga_jasaservice;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getId_jasaservice() {
        return id_jasaservice;
    }

    public void setId_jasaservice(int id_jasaservice) {
        this.id_jasaservice = id_jasaservice;
    }

    public String getNama_jasaservcie() {
        return nama_jasaservcie;
    }

    public void setNama_jasaservcie(String nama_jasaservcie) {
        this.nama_jasaservcie = nama_jasaservcie;
    }

    public String getHarga_jasaservice() {
        return harga_jasaservice;
    }

    public void setHarga_jasaservice(String harga_jasaservice) {
        this.harga_jasaservice = harga_jasaservice;
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
