package com.android.lib.base.adapter;

//by summer on 2017-06-20.

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.bean.AppViewHolder;

import java.util.ArrayList;

public class AppsDataBindingAdapter extends RecyclerView.Adapter<AppViewHolder> implements View.OnClickListener, View.OnLongClickListener {


    ArrayList list;
    int vari;
    int layout;
    private Context context;


    public AppsDataBindingAdapter(Context context, int layout, int vari, ArrayList list) {
        this.context = context;
        this.layout = layout;
        this.vari = vari;
        this.list = list;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false));
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.viewDataBinding;
        viewDataBinding.getRoot().setTag(R.id.data, list.get(position));
        viewDataBinding.getRoot().setTag(R.id.position, position);
        viewDataBinding.getRoot().setOnClickListener(this);
        viewDataBinding.getRoot().setOnLongClickListener(this);
        viewDataBinding.setVariable(vari, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }
}
