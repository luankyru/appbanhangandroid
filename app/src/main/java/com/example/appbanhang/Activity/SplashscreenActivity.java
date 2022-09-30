package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appbanhang.R;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (Exception ex ){

                }finally {
                    Intent intent = new Intent(getApplicationContext(),DangnhapActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        thread.start();
    }
}