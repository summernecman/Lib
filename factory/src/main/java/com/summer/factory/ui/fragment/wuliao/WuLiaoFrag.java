package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.bean.da.WuLiaoItemDABean;

import java.util.ArrayList;

public class WuLiaoFrag extends BaseUIFrag<WuLiaoUIOpe, WuLiaoDAOpe> {

    public static Fragment getInstance(Bundle bundle) {
        WuLiaoFrag wuLiaoFrag = new WuLiaoFrag();
        wuLiaoFrag.setArguments(bundle);
        return wuLiaoFrag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<WuLiaoItemDABean> wuliaodata = new ArrayList<>();
        WuLiaoItemDABean wuLiaoItemDABean = new WuLiaoItemDABean();
        wuLiaoItemDABean.a.set("物料1");
        wuLiaoItemDABean.b.set("物料2");
        wuLiaoItemDABean.c.set("物料3");
        wuliaodata.add(wuLiaoItemDABean);
        getOpes().getDa().setWuliaodata(wuliaodata);
        getOpes().getUi().initRecycle(getOpes().getDa().getWuliaodata());
    }
}
