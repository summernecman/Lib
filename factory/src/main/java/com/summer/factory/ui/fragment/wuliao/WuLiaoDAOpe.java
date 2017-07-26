package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

import java.util.ArrayList;

public class WuLiaoDAOpe extends BaseDAOpe {


    public WuLiaoDAOpe(Context context) {
        super(context);
    }

    public LayoutDABean getData(String[] str) {
        LayoutDABean bean = new LayoutDABean();
        for (int i = 0; str != null && i < str.length; i++) {
            bean.data[i].set(str[i]);
        }
        return bean;
    }

    public ArrayList<LayoutDABean> getRecycleData3() {
        ArrayList<LayoutDABean> wuliaodata = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            LayoutDABean wuLiaoItemDABean = new LayoutDABean();
            wuLiaoItemDABean.a.set("01");
            wuLiaoItemDABean.b.set("物料");
            wuLiaoItemDABean.c.set("3");
            wuliaodata.add(wuLiaoItemDABean);
        }
        return wuliaodata;
    }

    public ArrayList<LayoutDABean> getRecycleData4() {
        ArrayList<LayoutDABean> wuliaodata = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            LayoutDABean wuLiaoItemDABean = new LayoutDABean();
            wuLiaoItemDABean.a.set("01");
            wuLiaoItemDABean.b.set("物料");
            wuLiaoItemDABean.c.set("3");
            wuLiaoItemDABean.d.set("t");
            wuliaodata.add(wuLiaoItemDABean);
        }
        return wuliaodata;
    }

    public ArrayList<LayoutDABean> getRecycleData2() {
        ArrayList<LayoutDABean> wuliaodata = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            LayoutDABean wuLiaoItemDABean = new LayoutDABean();
            wuLiaoItemDABean.a.set("物料");
            wuLiaoItemDABean.b.set("3");
            wuliaodata.add(wuLiaoItemDABean);
        }
        return wuliaodata;
    }

    public LayoutDABean getTextEditData() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("采购单");
        return bean;
    }

    public LayoutDABean getTextEditData2() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("领料单");
        return bean;
    }

    public LayoutDABean getTextEditData3() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("原因");
        bean.b.set("更新ERP数量");
        return bean;
    }

    public LayoutDABean getTextEditData4() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("库位");
        return bean;
    }


    public LayoutDABean getRecycleTitleData3() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("序号");
        bean.b.set("品名");
        bean.c.set("未进");
        return bean;
    }

    public LayoutDABean getRecycleTitleData32() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("品名");
        bean.b.set("数量");
        bean.c.set("单位");
        return bean;
    }

    public LayoutDABean getRecycleTitleData33() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("数量");
        bean.b.set("库位条码");
        bean.c.set("库区代码");
        return bean;
    }

    public LayoutDABean getRecycleTitleData2() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("品名");
        bean.b.set("应领数量");
        return bean;
    }

    public LayoutDABean getRecycleTitleData22() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("工号");
        bean.b.set("姓名");
        return bean;
    }

    public LayoutDABean getRecycleTitleData23() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("工序");
        bean.b.set("工艺名");
        return bean;
    }


    public LayoutDABean getBottomData1() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("库位");
        bean.b.set("数量");
        bean.c.set(".");
        return bean;
    }

    public LayoutDABean getBottomData2() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("库位");
        bean.b.set("-->");
        bean.c.set("数量");
        bean.d.set(".");
        bean.e.set("PCS");
        bean.f.set("4-JG-403JG");
        return bean;
    }


    public LayoutDABean getBottomData3() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("员工码");
        return bean;
    }

    public LayoutDABean getBottomData4() {
        LayoutDABean bean = new LayoutDABean();
        bean.a.set("删除开工");
        return bean;
    }
}
