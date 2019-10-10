package com.example.bonasagala.atmaauto.model;

import com.google.gson.annotations.SerializedName;

public class Masuk {
    @SerializedName("USERNAME")
    private String username;

    @SerializedName("PASSWORD")
    private String password;

    @SerializedName("NAMA_PEGAWAI")
    private String nama_pegawai;

    @SerializedName("ROLE")
    private String role;

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

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
