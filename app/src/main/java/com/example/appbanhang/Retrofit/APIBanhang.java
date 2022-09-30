package com.example.appbanhang.Retrofit;

import com.example.appbanhang.Model.Donhang;
import com.example.appbanhang.Model.Donhangmodel;
import com.example.appbanhang.Model.MessageModel;
import com.example.appbanhang.Model.Thongke;
import com.example.appbanhang.Model.Thongkemodel;
import com.example.appbanhang.Model.Usermodel;
import com.example.appbanhang.Model.loaisanphammodel;
import com.example.appbanhang.Model.sanphammoimodel;

import java.util.Date;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIBanhang {
    @GET("getloaisanpham.php")
//    io.reactivex.rxjava3.core.Observable<loaisanphammodel>getloaisanpham();
    Observable<loaisanphammodel> getloaisanpham();

    @GET("getsanphammoi.php")
    Observable<sanphammoimodel> getsanphammoi();
    @GET("thongke.php")
    Observable<Thongkemodel> thongke();

    @POST("getsanpham.php")
    @FormUrlEncoded
    Observable<sanphammoimodel> getsanpham(
            @Field("page")int page,
            @Field("loai")int loai);

    @POST("dangky.php")
    @FormUrlEncoded
    Observable<Usermodel> dangky(
            @Field("email")String email,
            @Field("password")String password,
            @Field("username")String username,
            @Field("sodienthoai")String sodienthoai,
            @Field("uid")String uid,
            @Field("ngaysinh") String date

    );
    @POST("Dangnhap.php")
    @FormUrlEncoded
    Observable<Usermodel> dangnhap(
            @Field("email")String email,
            @Field("password")String password
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<Usermodel> createdonhang(
            @Field("iduser")int iduser,
            @Field("email")String email,
            @Field("diachi")String diachi,
            @Field("sodienthoai")String sodienthoai,
            @Field("soluong") int soluong,
            @Field("tongtien")String  tongtien,
            @Field("chitietdonhang")String chitietdonhang
    );

    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<Donhangmodel> xemdonhang(@Field("iduser")int iduser);

    @POST("search.php")
    @FormUrlEncoded
    Observable<sanphammoimodel> search(@Field("search")String search);

    @POST("themsanpham.php")
    @FormUrlEncoded
    Observable<sanphammoimodel> themsanpham(
            @Field("tensanpham")String tensanpham,
            @Field("giasanpham")long giasanpham,
            @Field("hinhanhsanpham")String hinhanhsanpham,
            @Field("mota") String mota,
            @Field("loai")int  loai
    );

    @POST("upload.php")
    Call<MessageModel> uploadFile(@Part MultipartBody.Part file);

    @POST("xoasanpham.php")
    @FormUrlEncoded
    Observable<sanphammoimodel> xoasanpham(
            @Field("id")int  id
    );
    @POST("updatetoken.php")
    @FormUrlEncoded
    Observable<MessageModel> updateToken(
            @Field("id")int  id,
            @Field("token")String token
    );





}
