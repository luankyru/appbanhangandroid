package com.example.appbanhang.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Activity.ThanhtoanActivity;
import com.example.appbanhang.Adapter.CustomItemAnimation;
import com.example.appbanhang.Adapter.giohangAdapter;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Ultils.Ultils;

import java.text.DecimalFormat;
import java.util.List;

public class Fragmentgiohang extends Fragment {
    RecyclerView recycleviewgiohang;
    public static ImageView imggohangtrong;
    public static TextView tvtongtien;
    public static Button btnthanhtoan,btntieptucmua;
    static giohangAdapter giohangAdapter;
    private List<sanpham> sanphams;
    MainActivity mainActivity = new MainActivity();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giohang,container,false);
        recycleviewgiohang = view.findViewById(R.id.recycleviewgiohang);
        imggohangtrong = view.findViewById(R.id.imggiohangtrong);
        tvtongtien = view.findViewById(R.id.tvtongtien);
        btnthanhtoan = view.findViewById(R.id.btnthanhtoan);
        btntieptucmua = view.findViewById(R.id.btntieptucmua);
        MainActivity.loaisp = 9;
        ActionToolBar();
        Tinhtongtien();
        return view;
    }
    public static void Tinhtongtien() {
        long tongtien= 0;
        for(int i = 0; i< Ultils.giohangArrayList.size(); i++){
            tongtien = tongtien +Ultils.giohangArrayList.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvtongtien.setText(decimalFormat.format(tongtien)+"Đ");
    }
    private void ActionToolBar(){
        recycleviewgiohang.setHasFixedSize(true);
        recycleviewgiohang.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleviewgiohang.setItemAnimator(new CustomItemAnimation());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recycleviewgiohang.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc xóa sản phẩm này ");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(Ultils.giohangArrayList.size()<=0){
                            Fragmentgiohang.imggohangtrong.setVisibility(View.VISIBLE);
                        }else{
                            int position = viewHolder.getPosition();
                            Ultils.giohangArrayList.remove(position);
                            Fragmentgiohang.adapterNotifonddatasetchange();
                            Fragmentgiohang.Tinhtongtien();
                            if(Ultils.giohangArrayList.size()<=0){
                                Fragmentgiohang.imggohangtrong.setVisibility(View.VISIBLE);
                                Fragmentgiohang.btnthanhtoan.setVisibility(View.GONE);

                            }else{
                                Fragmentgiohang.imggohangtrong.setVisibility(View.INVISIBLE);
                                Fragmentgiohang.adapterNotifonddatasetchange();
                                Fragmentgiohang.Tinhtongtien();

                            }
                            ((MainActivity)getActivity()).slgiohang();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Fragmentgiohang.adapterNotifonddatasetchange();
                        Fragmentgiohang.Tinhtongtien();
                    }
                });
                builder.show();
//                int i = viewHolder.getPosition();
//                Ultils.giohangArrayList.remove(i);
//                giohangAdapter.notifyDataSetChanged();
//                Tinhtongtien();
//                ((MainActivity)getActivity()).slgiohang();
            }

        });
        itemTouchHelper.attachToRecyclerView(recycleviewgiohang);
        if(Ultils.giohangArrayList.size()<=0){
            btnthanhtoan.setVisibility(View.GONE);
            recycleviewgiohang.setAdapter(giohangAdapter);
            imggohangtrong.setVisibility(View.VISIBLE);
            recycleviewgiohang.setVisibility(View.INVISIBLE);

        }
        else{
            giohangAdapter = new giohangAdapter(getContext(),Ultils.giohangArrayList);
            recycleviewgiohang.setAdapter(giohangAdapter);
            recycleviewgiohang.setItemAnimator(new CustomItemAnimation());
            giohangAdapter.notifyDataSetChanged();
        }


        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThanhtoanActivity.class);
                startActivity(intent);

            }
        });

    }
    public static void adapterNotifonddatasetchange(){
        giohangAdapter.notifyDataSetChanged();
    }
    public static void adapterItemremove(int i) {
        giohangAdapter.notifyItemRemoved(i);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
