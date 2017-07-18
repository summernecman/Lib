package com.summer.factory.ui.main;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

import java.util.ArrayList;

public class MainActDAOpe extends BaseDAOpe {


    ArrayList<LayoutDABean> txts = new ArrayList<>();


    public MainActDAOpe(Context context) {
        super(context);
        for (int i = 0; i < 24; i++) {
            LayoutDABean layoutDABean = new LayoutDABean();
            layoutDABean.a.set(i + "");
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
