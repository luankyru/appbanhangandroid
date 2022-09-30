package com.example.appbanhang.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Donhang {
    int id;
    @SerializedName("iduser")
    int iduser;
    String diachi;
    String tongtien;
    String sodienthoai;
    List<item> item;

    public Donhang(int id, int iduser, String diachi, String tongtien, String sodienthoai, List<item> item) {
        this.id = id;
        this.iduser = iduser;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.sodienthoai = sodienthoai;
        this.item = item;
    }

    public List<com.example.appbanhang.Model.item> getItem() {
        return item;
    }

    public void setItem(List<com.example.appbanhang.Model.item> item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }



}
