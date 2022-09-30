package com.example.appbanhang.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Adapter.SanphamAdapter;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragmentsanpham extends Fragment {
    RecyclerView recyclerView;
    int page = 1 ;
    APIBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ArrayList<sanpham> sanphamArrayList = new ArrayList<>();
    SanphamAdapter sanphamAdapter;
    MainActivity myActivity;
    public static ISendDataListener miSendDataListener;
    public interface ISendDataListener{
        void sendData(sanpham sanpham);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham,container,false);
        myActivity = (MainActivity) getActivity()   ;
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        recyclerView = view.findViewById(R.id.fragmentrcvsanpham);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData(MainActivity.loaisp);
    }



    private void getData(int loai) {
        compositeDisposable.add(apiBanhang.getsanpham(page,loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sanphammoimodel -> {
                            sanphamArrayList = (ArrayList<sanpham>) sanphammoimodel.getResult();
                            sanphamAdapter = new SanphamAdapter(sanphamArrayList,myActivity);
                            recyclerView.setAdapter(sanphamAdapter);
                            Toast.makeText(myActivity, "Thành công", Toast.LENGTH_SHORT).show();

                        }, throwable -> {
                            Toast.makeText(myActivity, "Kết nối đến server thất bại", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

}
