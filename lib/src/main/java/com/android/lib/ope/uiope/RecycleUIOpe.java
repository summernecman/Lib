package com.android.lib.ope.uiope;

//by summer on 2017-08-01.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.LayoutDABean;

import java.util.ArrayList;

public class RecycleUIOpe extends BaseUIOpe {


    public RecycleUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(RecyclerView recyclerView, int layout, int br, ArrayList<LayoutDABean> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new AppsDataBindingAdapter(context, layout, br, data));
    }
}
