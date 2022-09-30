package com.example.appbanhang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Adapter.sanphammoiAdapter;
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

public class Fragmenthome extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang;
    ArrayList<sanpham> sanphamArrayList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        viewFlipper = view.findViewById(R.id.vftrangchu);
        recyclerView = view.findViewById(R.id.rctrangchu);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        ActionViewFliper();
        getSanphammoi();


        return view;
    }

    private void ActionViewFliper() {
        List<String> manghinhanh = new ArrayList<String>();
        manghinhanh.add("https://vn.jugomobile.com/wp-content/uploads/2022/05/iPhone-14-day-la-hai-ban-nang-cap-toi.jpg");
        manghinhanh.add("https://cdn.tgdd.vn/Products/Images/44/263980/Slider/vi-vn-acer-nitro-5-gaming-an515-45-r6ev-r5-nhqbmsv006-1.jpg");
        manghinhanh.add("https://cdn.vox-cdn.com/thumbor/ADH7yQ8YCohTibQ3GmlQmTSKgt4=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/22015304/vpavic_4278_20201030_0247.jpg");
        for(int i = 0 ; i<manghinhanh.size(); i++){
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity()).load(manghinhanh.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_right);
        Animation slide_out= AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
    private void getSanphammoi() {
        compositeDisposable.add(apiBanhang.getsanphammoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sanphammoimodel -> {
                    if (sanphammoimodel.isSuccess()) {
                        sanphamArrayList = (ArrayList<sanpham>) sanphammoimodel.getResult();
                        sanphammoiAdapter sanphamAdapter = new sanphammoiAdapter(getActivity(), sanphamArrayList);
                        recyclerView.setAdapter(sanphamAdapter);


                    }
                }));

    }
}
