package com.summer.factory.ui.activity;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.ui.base.BaseFactoryAct;
import com.summer.factory.ui.fragment.tiaoma.TiaoMaFrag;
import com.summer.factory.ui.fragment.wuliao.WuLiao3Frag;

import java.util.ArrayList;

public class FrmOtherInAct extends BaseFactoryAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("03.其他入库");
        getOpes().getUi().viewDataBinding.title2.setTitle2(bean);

    }

    @Override
    public ArrayList<Fragment> initFrag() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(WuLiao3Frag.getInstance("物料"));
        fragments.add(TiaoMaFrag.getInstance("条码"));
        return fragments;
    }
}
