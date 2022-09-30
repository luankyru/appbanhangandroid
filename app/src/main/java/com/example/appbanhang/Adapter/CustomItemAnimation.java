package com.example.appbanhang.Adapter;

import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;

public class CustomItemAnimation extends DefaultItemAnimator {
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(
                holder.itemView.getContext(),
                R.anim.viewholder_remove_anim
        ));
        return super.animateRemove(holder);

    }

    @Override
    public long getRemoveDuration() {
        return 500;
    }

    @Override
    public long getAddDuration() {
        return 500;
    }
}
