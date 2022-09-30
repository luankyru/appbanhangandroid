package com.example.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Model.loaisanpham;
import com.example.appbanhang.R;

import java.util.ArrayList;

public class LoaispAdapter  extends BaseAdapter {
    ArrayList<loaisanpham> arraylistloaisp;
    Context context;

    @Override
    public int getCount() {
        return arraylistloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;

    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listviewloaisp,null);
            viewHolder.txttenloaisp = view.findViewById(R.id.tvsp);
            viewHolder.imgloaisp = view.findViewById(R.id.igmhinhanhsp);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();

        }
//        loaisanpham loaisanpham = (com.example.appbanhang.Model.loaisanpham) getItem(i);
        viewHolder.txttenloaisp.setText(arraylistloaisp.get(i).getTenloaisanpham());
        Glide.with(context).load(arraylistloaisp.get(i).getHinhanhsanpham()).into(viewHolder.imgloaisp);

        return view;
    }

    public LoaispAdapter(ArrayList<loaisanpham> arraylistloaisp, Context context) {
        this.arraylistloaisp = arraylistloaisp;
        this.context = context;
    }
}
