package com.summer.factory.ui.main;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

import java.util.ArrayList;

public class MainActDAOpe extends BaseDAOpe {


    public static final String[] titles = new String[]{"采购收料", "领料", "其他入库", "其他出库", "原材料移位", "原材料库位查询", "原材料图号查询", "流程票开工", "流程票开工修改", "流程票报工", "整体报废", "子件报废", "流程票调拨入库", "半成品盘点", "半成品采购收料", "半成品生产领料", "半成品整箱其他入库", "半成品零散其他入库", "半成品其他入库", "成品质检打包", "合格证装箱", "合格证绑定", "成品箱调拨入库", "成品采购入库", "成品包装上托", "成品拆箱", "成品拼箱", "销货确认", "盘点", "拣货查询", "现场盘点", "半成品整箱移位", "快捷码"};
    ArrayList<LayoutDABean> txts = new ArrayList<>();


    public MainActDAOpe(Context context) {
        super(context);
        for (int i = 0; i < titles.length; i++) {
            LayoutDABean layoutDABean = new LayoutDABean();
            layoutDABean.a.set(titles[i]);
            txts.add(layoutDABean);
        }
    }

    public ArrayList<LayoutDABean> getTxts() {
        return txts;
    }

    public void setTxts(ArrayList<LayoutDABean> txts) {
        this.txts = txts;
    }
}
