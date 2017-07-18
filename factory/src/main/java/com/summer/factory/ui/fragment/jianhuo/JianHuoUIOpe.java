package com.summer.factory.ui.fragment.jianhuo;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIBean;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragJianhuoBinding;

import java.util.ArrayList;

public class JianHuoUIOpe extends BaseUIBean<FragJianhuoBinding> {


    public JianHuoUIOpe(Context context) {
        super(context);
        initText();
    }

    public void initText() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("库位");
        viewDataBinding.setWuliao(bean);
        LayoutDABean bean2 = new LayoutDABean();
        bean2.a.set("数量");
        bean2.b.set("库位条码");
        bean2.c.set("库区代码");
        viewDataBinding.wuliaohead.setItemwuliao(bean2);
    }


    public void initRecycle(ArrayList<LayoutDABean> list) {
        viewDataBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        viewDataBinding.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_wuliao, BR.itemwuliao, list));
    }
}
