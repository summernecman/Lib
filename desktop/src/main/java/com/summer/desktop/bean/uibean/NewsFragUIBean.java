package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.view.other.AppMaterialRefreshLayout;

import butterknife.BindView;

public class NewsFragUIBean extends BaseUIBean {


    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.notelist)
    RelativeLayout notelist;
    @BindView(R.id.refresh)
    AppMaterialRefreshLayout refresh;

    public NewsFragUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_note_news);
    }

    public RecyclerView getRecycle() {
        return recycle;
    }

    public RelativeLayout getNotelist() {
        return notelist;
    }

    public AppMaterialRefreshLayout getRefresh() {
        return refresh;
    }
}
