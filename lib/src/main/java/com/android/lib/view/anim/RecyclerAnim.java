package com.android.lib.view.anim;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.android.lib.R;


/**
 * Created by ${viwmox} on 2016-10-09.
 */
public class RecyclerAnim extends DefaultItemAnimator {


    Context context;

    public RecyclerAnim(Context context) {
        this.context = context;
    }

    public RecyclerAnim() {
        super();
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_complex));
        return true;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return super.animateMove(holder, fromX, fromY, toX, toY);
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return super.animateRemove(holder);
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        super.endAnimation(item);
    }

    @Override
    public void endAnimations() {
        super.endAnimations();
    }

    @Override
    public boolean isRunning() {
        return super.isRunning();
    }

    @Override
    public void runPendingAnimations() {
        super.runPendingAnimations();
    }
}
