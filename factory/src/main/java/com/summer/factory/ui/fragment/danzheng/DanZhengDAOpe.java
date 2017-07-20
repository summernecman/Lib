package com.summer.factory.ui.fragment.danzheng;

//by summer on 2017-07-20.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

public class DanZhengDAOpe extends BaseDAOpe {


    public DanZhengDAOpe(Context context) {
        super(context);
    }


    public LayoutDABean getDanzhengData() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("流程票");
        bean.b.set("工序");
        bean.c.set("移除机器");
        bean.d.set("设备号");
        bean.e.set("时间");
        bean.f.set("2015");
        bean.g.set("11");
        bean.h.set("12");
        bean.i.set("当前时间");
        bean.j.set("删除开工");
        bean.k.set("进入报工");
        return bean;
    }
}
