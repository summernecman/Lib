package com.summer.factory.ui.fragment.lingliaodan;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

public class LingLiaoDanFrag extends BaseUIFrag<LingLiaoDanUIOpe, LingLiaoDanDAOpe> {

    public static Fragment getInstance(String title) {
        LingLiaoDanFrag liaoDanFrag = new LingLiaoDanFrag();
        liaoDanFrag.setArguments(new Bundle());
        liaoDanFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "领料单");
        return liaoDanFrag;
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
        getP().getD().setData(wuliaodata);
        getP().getU().initRecycle(getP().getD().getData());
    }
}
