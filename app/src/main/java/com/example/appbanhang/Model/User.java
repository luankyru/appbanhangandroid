package com.example.appbanhang.Model;

import java.util.Date;

public class User {
    int id ;
    String email;
    String password;
    String username;
    String sodienthoai;
    int admin;
    String uid;
    String ngaysinh;

    public int getAdmin() {
        return admin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User(){

    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public User(int id, String email, String password, String username, String sodienthoai, int admin, String uid, String ngaysinh) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.sodienthoai = sodienthoai;
        this.admin = admin;
        this.uid = uid;
        this.ngaysinh = ngaysinh;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
}
