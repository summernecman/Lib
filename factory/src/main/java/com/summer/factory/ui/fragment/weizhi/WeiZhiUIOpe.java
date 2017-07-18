package com.summer.factory.ui.fragment.weizhi;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragWeizhiBinding;

import java.util.ArrayList;

public class WeiZhiUIOpe extends BaseUIBean<FragWeizhiBinding> {


    public WeiZhiUIOpe(Context context) {
        super(context);
        initText();
    }

    public void initText() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("库位");
        bean.b.set("库区");
        viewDataBinding.title2.setItemtiaoma(bean);
    }


    public void initRecycle(ArrayList<LayoutDABean> list) {
        viewDataBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        viewDataBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tiaoma, BR.itemtiaoma, list));
    }
}
