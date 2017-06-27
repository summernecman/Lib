package com.android.lib.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.android.lib.R;

/**
 * recycleview适配器的基类
 */
public abstract class AppRecycleAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements View.OnClickListener, View.OnLongClickListener {

    /**
     * 上下文
     */
    protected Context context;
    /**
     * 布局解析器
     */
    protected LayoutInflater layoutInflater;
    /**
     * item点击事件
     */
    View.OnClickListener onClickListener;

    public AppRecycleAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.itemView.setTag(R.id.position, position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
