package com.example.appbanhang.Model;

public class item {
    int idsp;
    String tensanpham;
    int soluong;
    String hinhanhsanpham;

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }



    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public item(int idsp, String tensanpham, int soluong, String hinhanhsanpham) {
        this.idsp = idsp;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.hinhanhsanpham = hinhanhsanpham;
    }
}
