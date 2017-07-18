package com.summer.factory.ui.fragment.wuliao3;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragWuliao2Binding;

import java.util.ArrayList;

public class WuLiao3UIOpe extends BaseUIBean<FragWuliao2Binding> {


    public WuLiao3UIOpe(Context context) {
        super(context);
        initText();
    }

    public void initText() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("库位");
        bean.b.set("-->");
        bean.c.set("数量");
        bean.d.set(".");
        bean.d.set("PCS");
        bean.d.set("4pgreewg");
        viewDataBinding.setWuliao(bean);
        LayoutDABean bean2 = new LayoutDABean();
        bean2.a.set("品名");
        bean2.b.set("数量");
        bean2.c.set("单位");
        viewDataBinding.wuliaohead.setItemwuliao(bean2);
    }


    public void initRecycle(ArrayList<LayoutDABean> list) {
        viewDataBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        viewDataBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_wuliao, BR.itemwuliao, list));
    }
}
