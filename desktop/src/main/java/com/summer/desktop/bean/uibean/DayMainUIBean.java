package com.summer.desktop.bean.uibean;

//by summer on 2017-06-13.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.view.ItemDecoration.MyItemDecoration;

import butterknife.BindView;

public class DayMainUIBean extends BaseUIBean {


    @BindView(R.id.recycle)
    RecyclerView recycle;

    public DayMainUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_day_main);
    }


    public void initRecylce(RecyclerView.Adapter adapter) {
        getRecycle().setLayoutManager(new GridLayoutManager(context, 25));
        getRecycle().addItemDecoration(new MyItemDecoration(context, 1, Color.GRAY));
        getRecycle().setAdapter(adapter);
    }

    public RecyclerView getRecycle() {
        return recycle;
    }

}
