package com.example.appbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Fragment.Fragmentdangky;
import com.example.appbanhang.Model.User;
import com.example.appbanhang.Model.Usermodel;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangnhapActivity extends AppCompatActivity {
    TextView tvdangky,tvresetpass;
    EditText edtemail,edtpass;
    Button btndangnhap;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String email,password,userlogin,passlogin;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang;
    MainActivity mainActivity;
    LinearLayout linearLayout;
    FrameLayout frameLayout;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Anhxa();
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framecontext,fragment);
        fragmentTransaction.commit();
    }

    private void Anhxa() {

        mainActivity = new MainActivity();
        frameLayout = findViewById(R.id.framecontext);
        Paper.init(getApplicationContext());
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        edtemail = findViewById(R.id.edtemaildangnhap);
        edtpass = findViewById(R.id.edtpassdangnhap);
        btndangnhap = findViewById(R.id.btndangnhap);
        tvdangky= findViewById(R.id.tvdangky);
        linearLayout = findViewById(R.id.linear1);


        tvresetpass = findViewById(R.id.tvrepsetpass);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if(Paper.book().read("useremail") != null && Paper.book().read("userpass") != null){
            email = Paper.book().read("useremail").toString();
            password= Paper.book().read("userpass").toString();
            if(user!= null){
                getuserlogin();
            }else{
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(DangnhapActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    getuserlogin();
                                }
                            }
                        });
            }

        }else{
            btndangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email = edtemail.getText().toString();
                    password= edtpass.getText().toString();
                    getuserlogin();
                }
            });


        }
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                frameLayout.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
               replaceFragment(new Fragmentdangky());
            }
        });
        tvresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ResetpassActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
//        Fragmentdangky fragmentdangky =new Fragmentdangky();
//        fragmentdangky.onDestroy();
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Ultils.user_current.getEmail() != null && Ultils.user_current.getPass() != null){
            edtemail.setText(Ultils.user_current.getEmail());
            edtpass.setText(Ultils.user_current.getPass());
        }

    }
    public void getuserlogin(){
        if(TextUtils.isEmpty(email)==false && TextUtils.isEmpty(password)==false){
            compositeDisposable.add(apiBanhang.dangnhap(email,password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            usermodel -> {
                                if(usermodel.isSuccess()){
                                    Ultils.user_current = usermodel.getResult().get(0);
                                    Paper.book().write("useremail",usermodel.getResult().get(0).getEmail().trim());
                                    Paper.book().write("userpass",usermodel.getResult().get(0).getPass().trim());
                                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công"+Ultils.user_current.getSodienthoai(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Tài khoản hoặt mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                }
                            }, throwable -> {
                                Toast.makeText(getApplicationContext(), "Lấy dữ liệu thất bại quá", Toast.LENGTH_SHORT).show();
                            }));
        }else{
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();


    }
}