package com.example.appbanhang.Ultils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbanhang.Model.Giohang;
import com.example.appbanhang.Model.User;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;

import java.util.ArrayList;

public class Ultils {
    public static final String BASE_URL = "http://172.16.5.167/server/";//AAA
//    public static final String BASE_URL = "http://192.168.200.57/server/";
//    public static final String BASE_URL ="http://192.168.1.2/server/";//ở nhà
    public static ArrayList<Giohang> giohangArrayList;
    public static ArrayList<Giohang> mangmuahang = new ArrayList<>();

    public static User user_current =new User();
    public static sanpham sanphamchon = new sanpham();
}
