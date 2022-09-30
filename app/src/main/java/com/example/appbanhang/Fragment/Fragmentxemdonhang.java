package com.example.appbanhang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Adapter.DonhangAdapter;
import com.example.appbanhang.Model.Donhang;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragmentxemdonhang extends Fragment {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang;
    RecyclerView rcvdonhang ;
    ImageView donhangtrong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xemdonhang,container,false);
        rcvdonhang = view.findViewById(R.id.rcvdonhang);
        donhangtrong = view.findViewById(R.id.donghangtrong);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvdonhang.setLayoutManager(linearLayoutManager);
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        compositeDisposable.add(apiBanhang.xemdonhang(Ultils.user_current.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donhangmodel -> {
                            DonhangAdapter donhangAdapter = new DonhangAdapter(getContext(), donhangmodel.getResult());
                            if(donhangmodel.getResult().size()>0){
                                donhangtrong.setVisibility(View.GONE);
                                rcvdonhang.setVisibility(View.VISIBLE);
                                rcvdonhang.setAdapter(donhangAdapter);
                                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
                                rcvdonhang.addItemDecoration(itemDecoration);
                            }else{
                                donhangtrong.setVisibility(View.VISIBLE);
                                rcvdonhang.setVisibility(View.GONE);
                            }

                        }, throwable -> {
                        }));
        return view;
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
