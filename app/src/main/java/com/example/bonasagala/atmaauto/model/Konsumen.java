package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Konsumen {

    @SerializedName("ID_KONSUMEN")
    private int id_konsumen;
    @SerializedName("NAMA_KONSUMEN")
    private String nama_konsumen;
    @SerializedName("TELP_KONSUMEN")
    private String telp_konsumen;
    @SerializedName("ALAMAT_KONSUMEN")
    private String alamat_konsumen;
    @SerializedName("valueKonsumen")
    private String valueKonsumen;
    @SerializedName("messageKonsumen")
    private String messageKonsumen;

    public int getId_konsumen() {
        return id_konsumen;
    }

    public void setId_konsumen(int id_konsumen) {
        this.id_konsumen = id_konsumen;
    }

    public String getNama_konsumen() {
        return nama_konsumen;
    }

    public void setNama_konsumen(String nama_konsumen) {
        this.nama_konsumen = nama_konsumen;
    }

    public String getTelp_konsumen() {
        return telp_konsumen;
    }

    public void setTelp_konsumen(String telp_konsumen) {
        this.telp_konsumen = telp_konsumen;
    }

    public String getAlamat_konsumen() {
        return alamat_konsumen;
    }

    public void setAlamat_konsumen(String alamat_konsumen) {
        this.alamat_konsumen = alamat_konsumen;
    }

    public String getValueKonsumen() {
        return valueKonsumen;
    }

    public void setValueKonsumen(String valueKonsumen) {
        this.valueKonsumen = valueKonsumen;
    }

    public String getMessageKonsumen() {
        return messageKonsumen;
    }

    public void setMessageKonsumen(String messageKonsumen) {
        this.messageKonsumen = messageKonsumen;
    }
}
