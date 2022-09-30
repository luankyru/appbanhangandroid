package com.example.appbanhang.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Fragment.Fragmentgiohang;
import com.example.appbanhang.Interface.OnClickItemsanpham;
import com.example.appbanhang.Model.Giohang;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Ultils.Ultils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class giohangAdapter extends RecyclerView.Adapter<giohangAdapter.MyViewHolder> {
    Context context;

    List<Giohang> giohangArrayList;

    public giohangAdapter(Context context, ArrayList<Giohang> giohangArrayList) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham2,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        Giohang giohang = giohangArrayList.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvtensanphamgiohang.setText(giohang.getTensp());
        holder.tvgiasanphamgiohang.setText(decimalFormat.format(giohang.getGiasp())+" Đ");
        Glide.with(context).load(giohang.getHinhsp()).into(holder.imgsanphamgiohang);
        holder.btnvalue.setText(giohang.getSoluong()+"");

        int sl = Integer.parseInt(holder.btnvalue.getText().toString());
        if(sl >= 10){
            holder.imgcong.setVisibility(View.INVISIBLE);
            holder.imgtru.setVisibility(View.VISIBLE);

        }else if(sl<=1){
            holder.imgtru.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            holder.imgcong.setVisibility(View.VISIBLE);
            holder.imgtru.setVisibility(View.VISIBLE);

        }
        holder.imgcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnvalue.getText().toString())+1;
                int slht = Ultils.giohangArrayList.get(position).getSoluong();
                long giaht = Ultils.giohangArrayList.get(position).getGiasp();
                Ultils.giohangArrayList.get(position).setSoluong(slmoinhat);
                long giamoinhat  = (giaht*slmoinhat)/slht;
                Ultils.giohangArrayList.get(position).setGiasp(giamoinhat);
                holder.tvgiasanphamgiohang.setText(decimalFormat.format(Ultils.giohangArrayList.get(position).getGiasp())+" Đ");
                Fragmentgiohang.Tinhtongtien();
                if(slmoinhat >9){
                    holder.imgcong.setVisibility(View.INVISIBLE);
                    holder.imgtru.setVisibility(View.VISIBLE);
                    holder.btnvalue.setText(String.valueOf(slmoinhat));

                }
                else{
                    holder.imgcong.setVisibility(View.VISIBLE);
                    holder.imgtru.setVisibility(View.VISIBLE);
                    holder.btnvalue.setText(String.valueOf(slmoinhat));
                }
                ((MainActivity)context).slgiohang();
            }
        });
        holder.imgtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnvalue.getText().toString())-1;
                int slht = Ultils.giohangArrayList.get(position).getSoluong();
                long giaht = Ultils.giohangArrayList.get(position).getGiasp();
                Ultils.giohangArrayList.get(position).setSoluong(slmoinhat);
                long giamoinhat  = (giaht*slmoinhat)/slht;
                Ultils.giohangArrayList.get(position).setGiasp(giamoinhat);
                holder.tvgiasanphamgiohang.setText(decimalFormat.format(Ultils.giohangArrayList.get(position).getGiasp())+" Đ");
                Fragmentgiohang.Tinhtongtien();
                if(slmoinhat <2){
                    holder.imgtru.setVisibility(View.INVISIBLE);
                    holder.imgcong.setVisibility(View.VISIBLE);
                    holder.btnvalue.setText(String.valueOf(slmoinhat));
                }
                else{
                    holder.imgcong.setVisibility(View.VISIBLE);
                    holder.imgtru.setVisibility(View.VISIBLE);
                    holder.btnvalue.setText(String.valueOf(slmoinhat));
                }
                ((MainActivity)context).slgiohang();
            }
        });

        holder.setOnClickItemsanpham(new OnClickItemsanpham() {
            @Override
            public void onClicksanpham(View view, int pos, boolean iSLongClick) {

            }
        });
        holder.setOnLongClickItemsanpham(new OnClickItemsanpham() {
            @Override
            public void onClicksanpham(View view, int pos, boolean iSLongClick) {

                Toast.makeText(context, "San pham", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return giohangArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        CheckBox checkBox;
        TextView tvtensanphamgiohang,tvgiasanphamgiohang;
        ImageView imgsanphamgiohang,imgtru,imgcong;
        Button btnvalue;
        private OnClickItemsanpham onLongClickItemsanpham;
        private OnClickItemsanpham onClickItemsanpham;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            tvtensanphamgiohang =itemView.findViewById(R.id.tvtensanphamgiohang);
            tvgiasanphamgiohang =itemView.findViewById(R.id.tvgiasanphamgiohang);
            imgsanphamgiohang =itemView.findViewById(R.id.imgsanphamgiohang);
            imgtru =itemView.findViewById(R.id.imggiohangtru);
            imgcong =itemView.findViewById(R.id.imggiohangcong);
            btnvalue =itemView.findViewById(R.id.btnsoluonggiohang);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        public void setOnLongClickItemsanpham(OnClickItemsanpham onLongClickItemsanpham) {
            this.onLongClickItemsanpham = onLongClickItemsanpham;
        }
        @Override
        public boolean onLongClick(View view) {
            onLongClickItemsanpham.onClicksanpham(view ,getAdapterPosition(),true);
            return true;
        }

        public void setOnClickItemsanpham(OnClickItemsanpham onClickItemsanpham) {
            this.onClickItemsanpham = onClickItemsanpham;
        }

        @Override
        public void onClick(View view) {
            onClickItemsanpham.onClicksanpham(view,getAdapterPosition(),false);
        }


    }
}
