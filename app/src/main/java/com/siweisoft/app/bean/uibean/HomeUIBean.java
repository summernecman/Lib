package com.siweisoft.app.bean.uibean;

//by summer on 2017-03-31.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.siweisoft.app.IRecycle;
import com.siweisoft.app.R;
import com.summer.lib.base.adapter.AppRecycleAdapter;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.view.ItemDecoration.MyItemDecoration;

import butterknife.BindView;

public class HomeUIBean extends BaseUIBean implements IRecycle {

    @BindView(R.id.recycle)
    RecyclerView recyclerView;

    public HomeUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.frag_main);
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        recyclerView.addItemDecoration(new MyItemDecoration(context,2));
    }

    @Override
    public void load(AppRecycleAdapter appRecycleAdapter) {
        recyclerView.setAdapter(appRecycleAdapter);
    }


    @Override
    public void pull() {

    }

    @Override
    public void push() {

    }
    
}
