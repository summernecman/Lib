package com.summer.factory.ui.activity;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.ui.base.BaseFactoryAct;
import com.summer.factory.ui.fragment.danzheng.DanZhengFrag;
import com.summer.factory.ui.fragment.wuliao.YuanGongFrag;

import java.util.ArrayList;

public class FrmBeginWorkAct extends BaseFactoryAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("06.原材料库位查询");
        getOpes().U().bind.title2.setTitle2(bean);

    }

    @Override
    public ArrayList<Fragment> initFrag() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(DanZhengFrag.getInstance("单证"));
        fragments.add(YuanGongFrag.getInstance("员工"));
        return fragments;
    }
}
