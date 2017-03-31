package com.summer.lib.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ${viwmox} on 2016-08-29.
 */
public abstract class AppRecycleAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements View.OnClickListener {

    protected Context context;

    protected LayoutInflater layoutInflater;

    public AppRecycleAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


}
