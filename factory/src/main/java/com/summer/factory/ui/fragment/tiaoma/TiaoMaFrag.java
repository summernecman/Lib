package com.summer.factory.ui.fragment.tiaoma;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

public class TiaoMaFrag extends BaseUIFrag<TiaoMaUIOpe, TiaoMaDAOpe> {

    public static Fragment getInstance(String title) {
        TiaoMaFrag tiaoMaFrag = new TiaoMaFrag();
        tiaoMaFrag.setArguments(new Bundle());
        tiaoMaFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "条码");
        return tiaoMaFrag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<LayoutDABean> wuliaodata = new ArrayList<>();
        for (int i = 0; i < 34; i++) {
            LayoutDABean wuLiaoItemDABean = new LayoutDABean();
            wuLiaoItemDABean.a.set("物料a");
            wuLiaoItemDABean.b.set("物料2fd");
            wuLiaoItemDABean.c.set("物料fds3");
            wuliaodata.add(wuLiaoItemDABean);
        }
        getOpes().getDa().setWuliaodata(wuliaodata);
        getOpes().getUi().initRecycle(getOpes().getDa().getWuliaodata());
    }
}
