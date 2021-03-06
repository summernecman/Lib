package com.summer.factory.ui.fragment.lingliaodan;

//by summer on 2017-07-18.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.factory.BR;
import com.summer.factory.R;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragLiliaodanBinding;

import java.util.ArrayList;

public class LingLiaoDanUIOpe extends BaseUIOpe<FragLiliaodanBinding> {

    public LingLiaoDanUIOpe(Context context) {
        super(context);
        initText();
    }

    public void initText() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("领料单号");
        bind.title2.setItemlingliaodan(bean);
    }

    public void initRecycle(ArrayList<LayoutDABean> list) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_lingliaodan, BR.itemlingliaodan, list));
    }
}
