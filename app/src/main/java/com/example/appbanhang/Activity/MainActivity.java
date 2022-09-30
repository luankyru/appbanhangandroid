package com.example.appbanhang.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Adapter.LoaispAdapter;
import com.example.appbanhang.Adapter.SanphamAdapter;
import com.example.appbanhang.Adapter.sanphammoiAdapter;
import com.example.appbanhang.Fragment.Fragmentchitiet;
import com.example.appbanhang.Fragment.Fragmentgiohang;
import com.example.appbanhang.Fragment.Fragmenthome;
import com.example.appbanhang.Fragment.Fragmentmanager;
import com.example.appbanhang.Fragment.Fragmentsanpham;
import com.example.appbanhang.Fragment.Fragmentsearch;
import com.example.appbanhang.Fragment.Fragmentthongke;
import com.example.appbanhang.Fragment.Fragmentxemdonhang;
import com.example.appbanhang.Model.loaisanpham;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.Model.sanphammoimodel;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Checkconnection;
import com.example.appbanhang.Ultils.Ultils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  {
    public static final int Fragmenthome = 0;
    public static final int Fragmentsanpham = 1;
    public static final int Fragmentgiohang = 2;
    public static final int Fragmentsearch =4;
    public static final int Fragmentmanager=5;
    public static final int Fragmentmanageradd=6;
    public static final int Fragmentmanagerxoa=7;
    public static final int Fragmentthongke=7;
    public static final int Fragmentchitiet = 3;
    public static final int Fragmentxemdonhang = 8;
    public static final int Fragment = 2;
    public static int loaisp = 0;

    public static int Fragmentcurrent =Fragmenthome;
    Toolbar toolbar;
    ImageView imgsearch;
    FrameLayout frameLayout;

    TextView tvnameuser;
    CardView cardviewspmoi;
    ListView listViewmanhinhhinh;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ArrayList<loaisanpham> loaisanphamArrayList;
    LoaispAdapter loaispAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang;
    List<sanphammoimodel> sanphammoimodelList;
    NotificationBadge notificationBadge;
    ImageView imageViewcard;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        Anhxa();
        getToken();
        Actionbar();
        if(Checkconnection.haveNetworkConnection(this)==true){
            getDulieuloaisanpham();
            CatOnItemListView();
        }
        else{
            Toast.makeText(getApplicationContext(), "Kết nối thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out );
        fragmentTransaction.replace(R.id.Framefragment,fragment).addToBackStack(null).commit();
    }

    private void CatOnItemListView() {
        listViewmanhinhhinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                            if (loaisp != 1) {
                                loaisp = 1;
                                replaceFragment(new Fragmentsanpham());
                                Fragmentcurrent = Fragmentsanpham;
                                setTitleToolbar("Điện thoại");
                            }
                        break;
                    case 1:
                            if (loaisp != 2) {
                                loaisp = 2;
                                replaceFragment(new Fragmentsanpham());
                                Fragmentcurrent = Fragmentsanpham;
                                setTitleToolbar("Laptop");
                            }
                        break;
                    case 3:
                        if(Fragmentcurrent != Fragmentthongke){
                            replaceFragment(new Fragmentthongke());
                            Fragmentcurrent = Fragmentthongke;
                            setTitleToolbar("Thống kê");
                            loaisp = 0;
                        }
                        break;

                    case 5:
                        if (Fragmentcurrent != Fragmenthome) {
                            if (loaisp != 6) {
                                loaisp = 6;
                                replaceFragment(new Fragmentsanpham());
                                Fragmentcurrent = Fragmentsanpham;
                                setTitleToolbar("PS5");
                            }
                        }
                        break;
                    case 4:
                        if(Fragmentcurrent != Fragmenthome){
                            replaceFragment(new Fragmenthome());
                            Fragmentcurrent = Fragmenthome;
                            setTitleToolbar("Home");
                            loaisp = 0;
                        }
                        break;

                    case 6:
                        if(Fragmentcurrent != Fragmentxemdonhang){
                            replaceFragment(new Fragmentxemdonhang());
                            Fragmentcurrent = Fragmentxemdonhang;
                            setTitleToolbar("Xem đơn hàng");
                            loaisp = 0;
                        }
                        break;

                    case 7:
                        Paper.book().delete("useremail");
                        Paper.book().delete("userpass");
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this,DangnhapActivity.class);
                        startActivity(intent);
                        finish();

                        break;
                    case 8:
                        if(Fragmentcurrent != Fragmentmanager ){
                            replaceFragment(new Fragmentmanager());
                            Fragmentcurrent =Fragmentmanager ;
                            setTitleToolbar("Quản lý");
                            loaisp = 0;
                        }
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }
    public void setTitleToolbar(String title){
        toolbar.setTitle(title);
    }
    private void Actionbar() {

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.START);
//
//            }
//        });
        imageViewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Fragmentcurrent != Fragmentgiohang){
                    replaceFragment(new Fragmentgiohang());
                    Fragmentcurrent = Fragmentgiohang;
                }
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Fragmentcurrent != Fragmentsearch){
                    replaceFragment(new Fragmentsearch());
                    Fragmentcurrent = Fragmentsearch;
                }
            }
        });
    }

    private void getDulieuloaisanpham(){
        compositeDisposable.add(apiBanhang.getloaisanpham()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaisanphammodel -> {
                            if(loaisanphammodel.isSuccess()){
                                loaisanphamArrayList = (ArrayList<loaisanpham>) loaisanphammodel.getResult();
                                if(Ultils.user_current.isAdmin()==1 ){
                                    loaisanphamArrayList.add(new loaisanpham(20,"Quản lý","data:image/png;base64,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"));

                                }
                                loaispAdapter = new LoaispAdapter(loaisanphamArrayList,getApplicationContext());
                                listViewmanhinhhinh.setAdapter(loaispAdapter);



                            }
                        }
                ));
    }
    public void Anhxa(){
        frameLayout = findViewById(R.id.Framefragment);
        imgsearch = findViewById(R.id.imgsearch);
        toolbar = findViewById(R.id.toolbartrangchu);
        cardviewspmoi = findViewById(R.id.cardviewspmoi);
        tvnameuser = findViewById(R.id.tvnameuser);

        listViewmanhinhhinh = findViewById(R.id.lvnavigationview);
        navigationView = findViewById(R.id.navigationViewtrangchu);
        drawerLayout = findViewById(R.id.drawerLayouttrangchinh);
        notificationBadge = findViewById(R.id.slmenuct);
        imageViewcard = findViewById(R.id.imgcard);
        replaceFragment(new Fragmenthome());
        loaisanphamArrayList = new ArrayList<>();
        sanphammoimodelList = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvnameuser.setText(Ultils.user_current.getUsername());
        toolbar.setNavigationIcon(R.drawable.ic_baseline_format_list_bulleted);
        if(Ultils.giohangArrayList == null ){
            Ultils.giohangArrayList = new ArrayList<>();
        }


    }
    public void getToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if(!TextUtils.isEmpty(s)){
                            compositeDisposable.add(apiBanhang.updateToken(Ultils.user_current.getId(),s)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(messageModel ->{

                                    }, throwable -> {
                                        Log.d("log",throwable.getMessage());

                                    }));
                        }
                    }
                });
    }
    public void slgiohang(){
        int totalitem = 0 ;
        for(int i = 0 ;i < Ultils.giohangArrayList.size();i++){
            totalitem = totalitem +(Ultils.giohangArrayList.get(i).getSoluong());
        }
        notificationBadge.setText(String.valueOf(totalitem));
    }

    @Override
    protected void onResume() {
        super.onResume();
        slgiohang();

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.close();
        }else{
            super.onBackPressed();
        }


    }


}