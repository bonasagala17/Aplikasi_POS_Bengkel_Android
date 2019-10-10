package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Sparepart {
    @SerializedName("ID_SPAREPART")
    private int id_sparepart;
    @SerializedName("NAMA_SPAREPART")
    private String nama_sparepart;
    @SerializedName("MERK_SPAREPART")
    private String merk_sparepart;
    @SerializedName("TIPE_SPAREPART")
    private String tipe_sparepart;
    @SerializedName("HARGABELI_SPAREPART")
    private int hargabeli_sparepart;
    @SerializedName("HARGAJUAL_SPAREPART")
    private int hargajual_sparepart;
    @SerializedName("STOK_SPAREPART")
    private int stok_sparepart;
    @SerializedName("STOKOPTIMAL_SPAREPART")
    private int stokoptimal_sparepart;
    @SerializedName("GAMBAR_SPAREPART")
    private String gambar_sparepart;
    @SerializedName("LETAK_SPAREPART")
    private String letak_sparepart;
    @SerializedName("valueSparepart")
    private String valueSparepart;
    @SerializedName("messageSparepart")
    private String massageSparepart;

    public int getId_sparepart() {
        return id_sparepart;
    }

    public void setId_sparepart(int id_sparepart) {
        this.id_sparepart = id_sparepart;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getMerk_sparepart() {
        return merk_sparepart;
    }

    public void setMerk_sparepart(String merk_sparepart) {
        this.merk_sparepart = merk_sparepart;
    }

    public String getTipe_sparepart() {
        return tipe_sparepart;
    }

    public void setTipe_sparepart(String tipe_sparepart) {
        this.tipe_sparepart = tipe_sparepart;
    }

    public int getHargabeli_sparepart() {
        return hargabeli_sparepart;
    }

    public void setHargabeli_sparepart(int hargabeli_sparepart) {
        this.hargabeli_sparepart = hargabeli_sparepart;
    }

    public int getHargajual_sparepart() {
        return hargajual_sparepart;
    }

    public void setHargajual_sparepart(int hargajual_sparepart) {
        this.hargajual_sparepart = hargajual_sparepart;
    }

    public int getStok_sparepart() {
        return stok_sparepart;
    }

    public void setStok_sparepart(int stok_sparepart) {
        this.stok_sparepart = stok_sparepart;
    }

    public int getStokoptimal_sparepart() {
        return stokoptimal_sparepart;
    }

    public void setStokoptimal_sparepart(int stokoptimal_sparepart) {
        this.stokoptimal_sparepart = stokoptimal_sparepart;
    }

    public String getGambar_sparepart() {
        return gambar_sparepart;
    }

    public void setGambar_sparepart(String gambar_sparepart) {
        this.gambar_sparepart = gambar_sparepart;
    }

    public String getLetak_sparepart() {
        return letak_sparepart;
    }

    public void setLetak_sparepart(String letak_sparepart) {
        this.letak_sparepart = letak_sparepart;
    }

    public String getValueSparepart() {
        return valueSparepart;
    }

    public void setValueSparepart(String valueSparepart) {
        this.valueSparepart = valueSparepart;
    }

    public String getMassageSparepart() {
        return massageSparepart;
    }

    public void setMassageSparepart(String massageSparepart) {
        this.massageSparepart = massageSparepart;
    }
}
