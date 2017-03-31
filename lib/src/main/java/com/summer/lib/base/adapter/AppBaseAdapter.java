package com.summer.lib.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * Created by SWSD on 2016-04-20.
 */
public abstract class AppBaseAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    protected Context context;

    protected LayoutInflater layoutInflater;

    public AppBaseAdapter(Context context) {
        this.context = context;
        if (context == null) {
            return;
        }
        layoutInflater = LayoutInflater.from(context);
    }
}
