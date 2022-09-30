package com.example.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.Fragment.Fragmentchitiet;
import com.example.appbanhang.Interface.OnClickItemsanpham;
import com.example.appbanhang.Model.sanpham;
import com.example.appbanhang.R;
import com.example.appbanhang.Ultils.Ultils;

import java.text.DecimalFormat;
import java.util.List;

public class sanphammoiAdapter extends RecyclerView.Adapter<sanphammoiAdapter.ItemHolder> {
    Context context;
    List<sanpham> arraysanpham;
    sanpham sanpham;


    public sanphammoiAdapter(Context context, List<sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spmoi,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        sanpham sanpham = arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+ " Đ");
        Glide.with(context).load(sanpham.getHinhanhsanpham()).into(holder.imghinhanhsanpham);
        holder.setOnClickItemsanpham(new OnClickItemsanpham() {
            @Override
            public void onClicksanpham(View view, int pos, boolean iSLongClick) {
                Ultils.sanphamchon = sanpham;
                MainActivity.Fragmentcurrent = MainActivity.Fragmentchitiet;
                ((MainActivity)context).replaceFragment(new Fragmentchitiet());
                ((MainActivity)context).setTitleToolbar("Chi tiết");
//                Intent intent = new Intent(context, ChitietActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("sanpham",sanpham);
//                intent.putExtras(bundle);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 6;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imghinhanhsanpham;
        public TextView txttensanpham,txtgiasanpham;
        CardView cardviewspmoi ;
        private OnClickItemsanpham onClickItemsanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhanhsanpham = itemView.findViewById(R.id.imgsanpham);
            txttensanpham = itemView.findViewById(R.id.tvtensanpham);
            txtgiasanpham = itemView.findViewById(R.id.tvgia);
            cardviewspmoi = itemView.findViewById(R.id.cardviewspmoi);
            itemView.setOnClickListener(this);



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
