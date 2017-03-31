package com.summer.lib.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;

/**
 * Created by ${viwmox} on 2016-10-20.
 */

public abstract class BaseAppELVAdapter extends BaseExpandableListAdapter {


    protected Context context;

    protected LayoutInflater inflater;

    public BaseAppELVAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
}
