package com.summer.factory.ui.main;

//by summer on 2017-07-18.

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.ScreenUtil;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainActUIOpe extends BaseUIOpe<ActMainBinding> {


    public MainActUIOpe(Context context) {
        super(context);
    }

    public void initList(ArrayList<LayoutDABean> txts, final View.OnClickListener onClickListener) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main, BR.itemmain, txts) {

            @Override
            public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                AppViewHolder appViewHolder = new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main, parent, false));
                View view = appViewHolder.itemView.findViewById(R.id.itemmainiv);
                view.getLayoutParams().width = (int) (ScreenUtil.w / 4);
                view.getLayoutParams().height = (int) (ScreenUtil.w / 4);
                view.requestLayout();
                return appViewHolder;
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                onClickListener.onClick(v);
            }
        });
    }
}
