package com.android.lib.bean;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ViewDataBinding viewDataBinding;

    public AppViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }


}