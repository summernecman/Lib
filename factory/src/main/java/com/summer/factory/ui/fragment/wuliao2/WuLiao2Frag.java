package com.summer.factory.ui.fragment.wuliao2;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

public class WuLiao2Frag extends BaseUIFrag<WuLiao2UIOpe, WuLiao2DAOpe> {

    public static Fragment getInstance(String title) {
        WuLiao2Frag wuLiao2Frag = new WuLiao2Frag();
        wuLiao2Frag.setArguments(new Bundle());
        wuLiao2Frag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiao2Frag;
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
        getOpes().getDa().setWuliaodata(wuliaodata);
        getOpes().getUi().initRecycle(getOpes().getDa().getWuliaodata());
    }
}
