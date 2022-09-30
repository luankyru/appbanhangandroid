package com.example.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.Model.Donhang;
import com.example.appbanhang.Model.Donhangmodel;
import com.example.appbanhang.Model.item;
import com.example.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

public class DonhangAdapter extends RecyclerView.Adapter<DonhangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<Donhang> listdonhang;

    public DonhangAdapter(Context context, List<Donhang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Donhang donhang = listdonhang.get(position);
        holder.txtdonhang.setText("Đơn hàng "+donhang.getId()+":");
        holder.txttongia.setText("Tổng tiền :"+donhang.getTongtien());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.recyclerViewchitiet.getContext(),
                LinearLayoutManager.VERTICAL,false
        );
        linearLayoutManager.setInitialPrefetchItemCount(donhang.getItem().size());
        ChitietdonhangAdapter chitietdonhangAdapter = new ChitietdonhangAdapter(donhang.getItem());
        holder.recyclerViewchitiet.setLayoutManager(linearLayoutManager);
        holder.recyclerViewchitiet.setAdapter(chitietdonhangAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        holder.recyclerViewchitiet.addItemDecoration(itemDecoration);
        holder.recyclerViewchitiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listdonhang.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtdonhang,txttongia;
        RecyclerView recyclerViewchitiet ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txttongia = itemView.findViewById(R.id.tongtien);
            txtdonhang = itemView.findViewById(R.id.iddonhang);
            recyclerViewchitiet = itemView.findViewById(R.id.rcvchitiet);
        }
    }
}
