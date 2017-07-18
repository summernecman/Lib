package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragWuliaoBinding;

import java.util.ArrayList;

public class WuLiaoUIOpe extends BaseUIBean<FragWuliaoBinding> {


    public WuLiaoUIOpe(Context context) {
        super(context);
        initText();
    }

    public void initText() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("采购单");
        bean.b.set("库位");
        bean.c.set("数量");
        bean.d.set(".");
        viewDataBinding.setWuliao(bean);
        LayoutDABean bean2 = new LayoutDABean();
        bean2.a.set("序号");
        bean2.b.set("品名");
        bean2.c.set("未进");
        viewDataBinding.wuliaohead.setItemwuliao(bean2);
    }


    public void initRecycle(ArrayList<LayoutDABean> list) {
        viewDataBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        viewDataBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_wuliao, BR.itemwuliao, list));
    }
}
