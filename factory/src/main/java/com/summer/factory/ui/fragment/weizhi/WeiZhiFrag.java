package com.summer.factory.ui.fragment.weizhi;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

public class WeiZhiFrag extends BaseUIFrag<WeiZhiUIOpe, WeiZhiDAOpe> {

    public static Fragment getInstance(String title) {
        WeiZhiFrag weiZhiFrag = new WeiZhiFrag();
        weiZhiFrag.setArguments(new Bundle());
        weiZhiFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "位置");
        return weiZhiFrag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<LayoutDABean> wuliaodata = new ArrayList<>();
        LayoutDABean wuLiaoItemDABean = new LayoutDABean();
        wuLiaoItemDABean.a.set("物料1");
        wuLiaoItemDABean.b.set("物料2");
        wuLiaoItemDABean.c.set("物料3");
        wuliaodata.add(wuLiaoItemDABean);
        P().D().setData(wuliaodata);
        P().U().initRecycle(P().D().getData());
    }
}
