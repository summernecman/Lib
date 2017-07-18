package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.WuLiaoItemDABean;
import com.summer.factory.databinding.FragWuliaoBinding;

import java.util.ArrayList;

public class WuLiaoUIOpe extends BaseUIBean<FragWuliaoBinding> {


    public WuLiaoUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(ArrayList<WuLiaoItemDABean> list) {
        viewDataBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        viewDataBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_wuliao, BR.item_wuliao, list));
    }
}
