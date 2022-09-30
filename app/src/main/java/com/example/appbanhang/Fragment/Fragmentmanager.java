package com.example.appbanhang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Activity.ThanhtoanActivity;
import com.example.appbanhang.Adapter.SanphamAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import soup.neumorphism.NeumorphCardView;

public class Fragmentmanager extends Fragment {
    private ViewPager2 viewPager2;
    BottomNavigationView bottomNavigationView ;
    public static boolean isAdmin;
    APIBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager,container,false);
        bottomNavigationView = view.findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.them:
                        Toast.makeText(getActivity(), "Thêm", Toast.LENGTH_SHORT).show();
                        if(MainActivity.Fragmentcurrent !=MainActivity.Fragmentmanageradd){
                            MainActivity.Fragmentcurrent = MainActivity.Fragmentmanageradd;
                            replaceFragment(new Fragmentthemsanpham());
                        }
                        break;
                    case R.id.xoa:
                        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
                        if(MainActivity.Fragmentcurrent !=MainActivity.Fragmentmanagerxoa){
                            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,
                                    R.anim.fade_out,
                                    R.anim.fade_in,
                                    R.anim.slide_out );
                            fragmentTransaction.replace(R.id.framefragmentsearchxoa,new Fragmentsearch()).addToBackStack(null).commit();
                            isAdmin =true;
                            MainActivity.Fragmentcurrent =MainActivity.Fragmentmanagerxoa;


                        }else{
//                            if(SanphamAdapter.mangxoasp.size()<= 0){
//                            }else{
//                                for (int i = 0 ; i<SanphamAdapter.mangxoasp.size();i++){
//                                    compositeDisposable.add(apiBanhang.xoasanpham(SanphamAdapter.mangxoasp.get(i).getId())
//                                            .subscribeOn(Schedulers.io())
//                                            .observeOn(AndroidSchedulers.mainThread())
//                                            .subscribe(usermodel -> {
//                                                        Toast.makeText(getContext(), "xóa thành công", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                    ,throwable -> {
//                                                        Toast.makeText(getContext(), "xóa thất bại", Toast.LENGTH_SHORT).show();
//                                                    }
//                                            ));
//                                }
//                            }
                        }
                        break;
                    case R.id.sua:
                        break;
                }
                return true;
            }
        });
        return view;
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out );
        fragmentTransaction.replace(R.id.framefragmentsearchxoa,fragment).addToBackStack(null).commit();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        isAdmin =false;

        super.onStop();
    }
}
