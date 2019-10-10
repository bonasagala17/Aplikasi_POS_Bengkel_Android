package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Pegawai {
    @SerializedName("ID_PEGAWAI")
    private int id_pegawai;
    @SerializedName("NAMA_PEGAWAI")
    private String nama_pegawai;
    @SerializedName("ALAMAT_PEGAWAI")
    private String alamat_pegawai;
    @SerializedName("TELP_PEGAWAI")
    private String telp_pegawai;
    @SerializedName("GAJI_PEGAWAI")
    private String gaji_pegawai;
    @SerializedName("ROLE")
    private String role;
    @SerializedName("USERNAME")
    private String username;
    @SerializedName("PASSWORD")
    private String password;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(int id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getAlamat_pegawai() {
        return alamat_pegawai;
    }

    public void setAlamat_pegawai(String alamat_pegawai) {
        this.alamat_pegawai = alamat_pegawai;
    }

    public String getTelp_pegawai() {
        return telp_pegawai;
    }

    public void setTelp_pegawai(String telp_pegawai) {
        this.telp_pegawai = telp_pegawai;
    }

    public String getGaji_pegawai() {
        return gaji_pegawai;
    }

    public void setGaji_pegawai(String gaji_pegawai) {
        this.gaji_pegawai = gaji_pegawai;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
