package com.summer.factory.ui.activity;

//by summer on 2017-07-17.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.ui.base.BaseFactoryAct;
import com.summer.factory.ui.fragment.tiaoma.TiaoMaFrag;
import com.summer.factory.ui.fragment.wuliao.WuLiaoFrag;

import java.util.ArrayList;

public class FrmPurReceiveAct extends BaseFactoryAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("01.采购收料");
        getP().getU().bind.title2.setTitle2(bean);

    }

    @Override
    public ArrayList<Fragment> initFrag() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(WuLiaoFrag.getInstance("物料"));
        fragments.add(TiaoMaFrag.getInstance("条码"));
        return fragments;
    }

}
