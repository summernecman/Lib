package com.android.lib.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * listview的适配器基类
 */
public abstract class AppBaseAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    protected Context context;
    /**
     * 布局解析器
     */
    protected LayoutInflater layoutInflater;

    public AppBaseAdapter(Context context) {
        this.context = context;
        if (context == null) {
            return;
        }
        layoutInflater = LayoutInflater.from(context);
    }
}
