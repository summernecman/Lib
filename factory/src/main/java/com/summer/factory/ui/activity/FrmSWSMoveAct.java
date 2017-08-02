package com.summer.factory.ui.activity;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.ui.base.BaseFactoryAct;
import com.summer.factory.ui.fragment.wuliao.FrmSWSMoveFrag;

import java.util.ArrayList;

public class FrmSWSMoveAct extends BaseFactoryAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("32.半成品整箱移位");
        getOpes().U().bind.title2.setTitle2(bean);

    }

    @Override
    public ArrayList<Fragment> initFrag() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(FrmSWSMoveFrag.getInstance("条码"));
        return fragments;
    }
}
