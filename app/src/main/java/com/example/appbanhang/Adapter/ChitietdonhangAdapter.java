package com.example.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Model.item;
import com.example.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

public class ChitietdonhangAdapter extends RecyclerView.Adapter<ChitietdonhangAdapter.MyViewHolder> {
    List<item> itemsdonhang;
    Context context;
    public ChitietdonhangAdapter(List<item> itemsdonhang) {
        this.itemsdonhang = itemsdonhang;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitietdonhang,parent,false);
        context = view.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        item item = itemsdonhang.get(position);
        holder.tvtenitem.setText("Tên sản phẩm :"+item.getTensanpham());
        holder.tvcount.setText("Số lượng :"+item.getSoluong());
        if(context.getApplicationContext()!=null){
            Glide.with(this.context.getApplicationContext()).load(item.getHinhanhsanpham()).into(holder.imghinhanhsp);
        }

    }

    @Override
    public int getItemCount() {
        return itemsdonhang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvtenitem,tvcount;
        ImageView imghinhanhsp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenitem = itemView.findViewById(R.id.tensanpham);
            tvcount = itemView.findViewById(R.id.count);
            imghinhanhsp = itemView.findViewById(R.id.imgitemhinhanhsp);


        }
    }
}
