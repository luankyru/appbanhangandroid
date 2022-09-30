package com.example.appbanhang.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Fragment.Fragmentchitiet;
import com.example.appbanhang.Fragment.Fragmentgiohang;
import com.example.appbanhang.Fragment.Fragmentmanager;
import com.example.appbanhang.Fragment.Fragmentsearch;
import com.example.appbanhang.Interface.OnClickItemsanpham;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Ultils.Ultils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.MyViewHolder> {

    Context context ;
    ArrayList<sanpham> sanphamArrayList;
    Fragmentsearch fragmentsearch = new Fragmentsearch();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        sanpham sanpham = sanphamArrayList.get(position);

        holder.tvtendt.setText(sanpham.getTensanpham());

        holder.tvtendt.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvgiadt.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham()) +"Đ");
        Glide.with(context).load(sanpham.getHinhanhsanpham()).into(holder.imghinhanhdt);
        holder.setOnClickItemsanpham(new OnClickItemsanpham() {
            @Override
            public void onClicksanpham(View view, int pos, boolean iSLongClick) {
//                Intent intent = new Intent(context,ChitietActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("sanpham",sanpham);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
                if(!iSLongClick){
                    if(MainActivity.Fragmentcurrent !=MainActivity.Fragmentchitiet ){
                        if (context instanceof MainActivity ) {
                            Ultils.sanphamchon = sanpham;
                            MainActivity.loaisp = 0;
                           MainActivity.Fragmentcurrent = MainActivity.Fragmentchitiet;
                            ((MainActivity)context).replaceFragment(new Fragmentchitiet());
                            ((MainActivity)context).setTitleToolbar("Chi tiết");
                        }
                    }
                }
            }
        });
        holder.setOnLongClickItemsanpham(new OnClickItemsanpham() {
            @Override
            public void onClicksanpham(View view, int pos, boolean iSLongClick) {
                if(Ultils.user_current.isAdmin() ==1){
                    fragmentsearch.xoasanpham(sanpham.getId());
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return sanphamArrayList.size() ;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView tvtendt,tvgiadt;
        ImageView imghinhanhdt;
        CardView lnitemsp;
        CheckBox  checkBox;
        private OnClickItemsanpham onClickItemsanpham;
        private OnClickItemsanpham onLongClickItemsanpham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvgiadt= itemView.findViewById(R.id.tvitemgialaptop);
            tvtendt = itemView.findViewById(R.id.tvitemtenlaptop);
            imghinhanhdt = itemView.findViewById(R.id.imgitemlaptop);
            lnitemsp = itemView.findViewById(R.id.layoutitem);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setOnClickItemsanpham(OnClickItemsanpham onClickItemsanpham) {
            this.onClickItemsanpham = onClickItemsanpham;
        }
        @Override
        public void onClick(View view) {
            onClickItemsanpham.onClicksanpham(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            onLongClickItemsanpham.onClicksanpham(view ,getAdapterPosition(),true);
            return true;
        }

        public void setOnLongClickItemsanpham(OnClickItemsanpham onLongClickItemsanpham) {
            this.onLongClickItemsanpham = onLongClickItemsanpham;
        }
    }

    public SanphamAdapter(ArrayList<sanpham> dienthoaiArrayList, Context context) {

        this.context = context;
        this.sanphamArrayList = dienthoaiArrayList;
    }
}

