package com.example.appbanhang.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Adapter.SanphamAdapter;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragmentsearch extends Fragment {
    Context context;
    APIBanhang apiBanhang;
    public EditText edtsearch;
    RecyclerView recyclerViewsearch ;
    List<sanpham> sanphams = new ArrayList<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        edtsearch = view.findViewById(R.id.edtsearch);
        recyclerViewsearch = view.findViewById(R.id.rcvsearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewsearch.setHasFixedSize(true);
        recyclerViewsearch.setLayoutManager(linearLayoutManager);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0)
                {
                    sanphams.clear();
                    SanphamAdapter sanphamAdapter = new SanphamAdapter((ArrayList<sanpham>) sanphams,getContext());
                    recyclerViewsearch.setAdapter(sanphamAdapter);
                }else{
                    getdata(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void getdata(String s) {
        sanphams.clear();
        compositeDisposable.add(apiBanhang.search(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sanphammoimodel -> {
                            sanphams = sanphammoimodel.getResult();
                            SanphamAdapter sanphamAdapter = new SanphamAdapter((ArrayList<sanpham>) sanphams,getContext());
                            recyclerViewsearch.setAdapter(sanphamAdapter);
                        }, throwable -> {
                            Toast.makeText(getContext(), "Kết nối đến server thất bại", Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    public void xoasanpham (int idsp ){
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        compositeDisposable.add(apiBanhang.xoasanpham(idsp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(usermodel -> {
//                            Toast.makeText(context.getApplicationContext(), "xóa thành công", Toast.LENGTH_SHORT).show();

                        }
                        ,throwable -> {
//                            Toast.makeText(context.getApplicationContext(), "xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

}
