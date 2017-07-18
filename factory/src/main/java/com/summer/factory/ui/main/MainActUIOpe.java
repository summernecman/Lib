package com.summer.factory.ui.main;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainActUIOpe extends BaseUIBean<ActMainBinding> {


    public MainActUIOpe(Context context) {
        super(context);
    }

    public void initList(ArrayList<LayoutDABean> txts, final View.OnClickListener onClickListener) {
        viewDataBinding.recycle.setLayoutManager(new GridLayoutManager(context, 5));
        viewDataBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main, BR.itemmain, txts) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                onClickListener.onClick(v);
            }
        });
    }
}
