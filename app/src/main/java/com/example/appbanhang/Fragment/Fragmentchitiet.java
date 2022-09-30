package com.example.appbanhang.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Model.Giohang;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Ultils.Ultils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class Fragmentchitiet extends Fragment {
    TextView tvtenchitiecsanpham,tvgiachitiecsanpham,tvmotachitiecsanpham;
    ImageView imgchitiecsanpham,iconcard;
    Spinner spinnersoluongmua;
    Button btnthemgiohang;
    com.example.appbanhang.Model.sanpham sanpham;
    NotificationBadge notificationBadge;
     int soluong ;
     MainActivity myActivity = new MainActivity();
    Context context ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiet,container,false);
        btnthemgiohang = view.findViewById(R.id.btnthemgiohang);
        tvtenchitiecsanpham = view.findViewById(R.id.tvtenchitiecsanpham);
        tvgiachitiecsanpham = view.findViewById(R.id.tvgiachitiecsanpham);
        tvmotachitiecsanpham = view.findViewById(R.id.tvmotachitiecsanpham);
        imgchitiecsanpham = view.findViewById(R.id.imgchitiecsanpham);
        spinnersoluongmua = view.findViewById(R.id.Spinnersoluong);
        Setdata ();
        Eventbutton();

        return view ;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void Eventbutton(){
        btnthemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.loaisp = 0;
                themgiohang();
            }
        });
    }

    void themgiohang(){
        if(Ultils.giohangArrayList.size()>0){
            int soluong = Integer.parseInt(spinnersoluongmua.getSelectedItem().toString());
            int totalitem = 0 ;
            boolean exists = false;
            for(int i = 0;i<Ultils.giohangArrayList.size();i++){
                if(Ultils.giohangArrayList.get(i).getIdsp()==Ultils.sanphamchon.getId()){
                    Ultils.giohangArrayList.get(i).setSoluong(soluong +Ultils.giohangArrayList.get(i).getSoluong());
                    long gia = Ultils.giohangArrayList.get(i).getSoluong() * Ultils.sanphamchon.getGiasanpham();
                    totalitem = totalitem +(Ultils.giohangArrayList.get(i).getSoluong());
                    Ultils.giohangArrayList.get(i).setGiasp(gia );
                    exists= true;
                }
            }
            if(exists ==false){
                long gia = soluong * Ultils.sanphamchon.getGiasanpham();
                Ultils.giohangArrayList.add(new Giohang(Ultils.sanphamchon.getId(),Ultils.sanphamchon.getTensanpham(),gia,Ultils.sanphamchon.getHinhanhsanpham(),soluong));
            }
        }
        else{
            soluong = Integer.parseInt(spinnersoluongmua.getSelectedItem().toString());
            long giamoi = soluong * Ultils.sanphamchon.getGiasanpham();
            Ultils.giohangArrayList.add(new Giohang(Ultils.sanphamchon.getId(),Ultils.sanphamchon.getTensanpham(),giamoi,Ultils.sanphamchon.getHinhanhsanpham(),soluong));
            int totalitem = 0 ;
            for(int i = 0 ;i < Ultils.giohangArrayList.size();i++){
                totalitem = totalitem +(Ultils.giohangArrayList.get(i).getSoluong());
            }
//            notificationBadge.setText(String.valueOf(totalitem));

        }
        ((MainActivity)getActivity()).slgiohang();
    }
    public void Setdata (){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvtenchitiecsanpham.setText(Ultils.sanphamchon.getTensanpham());
        tvmotachitiecsanpham.setText(Ultils.sanphamchon.getMotosanpham());
        tvgiachitiecsanpham.setText("Giá: "+decimalFormat.format(Ultils.sanphamchon.getGiasanpham()) +"Đ");
        Glide.with(getActivity()).load(Ultils.sanphamchon.getHinhanhsanpham()).into(imgchitiecsanpham);
        Integer []soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_checked,soluong);
        spinnersoluongmua.setAdapter(arrayAdapter);
    }



}
