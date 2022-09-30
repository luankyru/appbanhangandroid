package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Fragment.Fragmentgiohang;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThanhtoanActivity extends AppCompatActivity {
    Button btnxacnhan,btntrove;
    TextView tvtongtienthanhtoan;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang;
    int totalitem = 0 ;
    Fragmentgiohang fragmentgiohang = new Fragmentgiohang();

    EditText edtTenkhachhang,edtmailkhachang,edtsdtkhachhang,edtlocationkhachhang;
    private String amount = "10000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "Thanh toán đơn hàng ";
    private String merchantCode = "SCB01";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description = "Mua hàng tại app bán hàng";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        for(int i = 0 ;i < Ultils.giohangArrayList.size();i++){
            totalitem = totalitem +(Ultils.giohangArrayList.get(i).getSoluong());
        }
        Anhxa();
    }

    private void Filldata() {
        edtmailkhachang.setText(Ultils.user_current.getEmail());
        edtsdtkhachhang.setText(Ultils.user_current.getSodienthoai());
        tvtongtienthanhtoan.setText(Fragmentgiohang.tvtongtien.getText());

    }

    private void Anhxa() {
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        tvtongtienthanhtoan = findViewById(R.id.tvtongtienthanhtoan);
        edtTenkhachhang = findViewById(R.id.edttenkhachhang);
        edtmailkhachang = findViewById(R.id.edtmailkhachang);
        edtsdtkhachhang = findViewById(R.id.edtsdtkhachhang);
        edtlocationkhachhang = findViewById(R.id.edtlocationkhachhang);
        Filldata();
        btnxacnhan = findViewById(R.id.btnxacnhan);

        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                String tenkhachhang = edtTenkhachhang.getText().toString().trim();
                String diachikhachhang =edtlocationkhachhang.getText().toString().trim();
                String sodienthoaikhachahng =edtsdtkhachhang.getText().toString().trim();
                String emailkhachhang = edtmailkhachang.getText().toString().trim();
                if(TextUtils.isEmpty(tenkhachhang)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhâp tên khách hàng", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(diachikhachhang)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ khách hàng", Toast.LENGTH_SHORT).show();
                }else{

                    Log.d("TEST",new Gson().toJson(Ultils.giohangArrayList));
                    compositeDisposable.add(apiBanhang.createdonhang(Ultils.user_current.getId(),emailkhachhang,diachikhachhang,sodienthoaikhachahng,totalitem, fragmentgiohang.tvtongtien.getText().toString(),new Gson().toJson(Ultils.giohangArrayList))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(usermodel -> {
                                Toast.makeText(ThanhtoanActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                Ultils.giohangArrayList.clear();
                                Intent intent = new Intent(ThanhtoanActivity.this,MainActivity.class);
                                startActivity(intent);

                                finish();

                            }
                            ,throwable -> {
                                Toast.makeText(ThanhtoanActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
        btntrove = findViewById(R.id.btntrove);
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }

}