package com.example.appbanhang.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class sanpham implements Serializable {
    @SerializedName("id")
    public int Id;
    public String tensanpham;
    public Integer giasanpham;
    public String hinhanhsanpham;

    @SerializedName("mota")
    public String motosanpham;

    @SerializedName("loai")
    public int Idsanpham;
    public sanpham(){

    }

    public sanpham(int id, String tensanpham, Integer giasanpham, String hinhanhsanpham, String motosanpham, int idsanpham) {
        Id = id;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.motosanpham = motosanpham;
        Idsanpham = idsanpham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public Integer getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotosanpham() {
        return motosanpham;
    }

    public void setMotosanpham(String motosanpham) {
        this.motosanpham = motosanpham;
    }

    public int getIdsanpham() {
        return Idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        Idsanpham = idsanpham;
    }
}
