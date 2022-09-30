package com.example.appbanhang.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbanhang.Activity.DangnhapActivity;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Model.User;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragmentdangky extends Fragment {
    EditText edtemail,edtpass,edtrepass,edtsodienthoai,edtusername,edtdob;
    ImageView imgcalender;
    Button btndangky;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky,container,false);
        edtemail = view.findViewById(R.id.edtemaildangky);
        edtpass = view.findViewById(R.id.edtpassdangky);
        edtrepass = view.findViewById(R.id.edtrepassdangky);
        edtsodienthoai= view.findViewById(R.id.edtsodienthoaidangky);
        edtusername = view.findViewById(R.id.edtusernamedangky);
        edtdob = view.findViewById(R.id.edtdob);

        btndangky = view.findViewById(R.id.btndangky);
        imgcalender= view.findViewById(R.id.imgcalender);
        imgcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chonngay();
            }
        });

        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void login() {
        String email = edtemail.getText().toString().trim();
        String password=edtpass.getText().toString().trim();
        String repassword = edtrepass.getText().toString().trim();
        String username= edtusername.getText().toString().trim();
        String sodienthoai= edtsodienthoai.getText().toString().trim();
        String ngaysinh = edtdob.getText().toString().trim();
//        Validatafform ();
        if(TextUtils.isEmpty(email)==false && TextUtils.isEmpty(password)==false&&TextUtils.isEmpty(repassword)==false &&TextUtils.isEmpty(sodienthoai)==false&&TextUtils.isEmpty(username)==false){
            if(repassword.equals(password)){
                firebaseAuth =FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if(user != null){
                                        postData(email,password ,username,sodienthoai,user.getUid(),ngaysinh);
                                        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                    }else{
                                        Toast.makeText(getContext(), "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getContext(), "Tài khoản hoặt mật khẩu đã tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
            else{
                Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(email.isEmpty()){
                edtemail.setHint("");
                edtemail.setHintTextColor(getResources().getColor(R.color.red));
                edtemail.setHint("Vui long nhập email");

            }else if(password.isEmpty()){
                edtemail.setHint("");
                edtpass.setHintTextColor(getResources().getColor(R.color.red));
                edtpass.setHint("Vui long nhập password");
            }else if(repassword.isEmpty()){
                edtemail.setHint("");
                edtrepass.setHintTextColor(getResources().getColor(R.color.red));
                edtrepass.setHint("Vui long nhập lại password");
            }else if(username.isEmpty()){
                edtemail.setHint("");
                edtusername.setHintTextColor(getResources().getColor(R.color.red));
                edtusername.setHint("Vui long nhập Username");
            }else if(sodienthoai.isEmpty()){
                edtemail.setHint("");
                edtsodienthoai.setHintTextColor(getResources().getColor(R.color.red));
                edtsodienthoai.setHint("Vui long nhập số điện thoại");
            }
        }

    }
    private void Chonngay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                edtdob.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    public void postData(String email, String password , String username, String sodienthoai, String uid, String ngaysinh){
        compositeDisposable.add(apiBanhang.dangky(email,password,username,sodienthoai,uid,ngaysinh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        Usermodel -> {
                            if(Usermodel.isSuccess()){
                                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Ultils.user_current.setEmail(email);
                                Ultils.user_current.setPass(password);

                                Intent intent = new Intent(getContext(), DangnhapActivity.class);
                                startActivity(intent);
                            }
                        }, throwable -> {
                            Toast.makeText(getContext(), "Lấy dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                        }));

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

