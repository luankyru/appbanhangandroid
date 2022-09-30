package com.example.appbanhang.Model;

public class loaisanpham {
    private int id;
     String tenloaisanpham;
     String hinhanhsanpham;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public loaisanpham(int id, String tenloaisanpham, String hinhanhsanpham) {
        this.id = id;
        this.tenloaisanpham = tenloaisanpham;
        this.hinhanhsanpham = hinhanhsanpham;
    }
}
