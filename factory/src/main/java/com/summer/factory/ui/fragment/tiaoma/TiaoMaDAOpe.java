package com.summer.factory.ui.fragment.tiaoma;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

import java.util.ArrayList;

public class TiaoMaDAOpe extends BaseDAOpe {

    ArrayList<LayoutDABean> wuliaodata;

    public TiaoMaDAOpe(Context context) {
        super(context);
    }

    public ArrayList<LayoutDABean> getWuliaodata() {
        return wuliaodata;
    }

    public void setWuliaodata(ArrayList<LayoutDABean> wuliaodata) {
        this.wuliaodata = wuliaodata;
    }
}
