package com.summer.factory.ui.fragment.wuliao3;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

import java.util.ArrayList;

public class WuLiao3DAOpe extends BaseDAOpe {

    ArrayList<LayoutDABean> wuliaodata;

    public WuLiao3DAOpe(Context context) {
        super(context);
    }

    public ArrayList<LayoutDABean> getWuliaodata() {
        return wuliaodata;
    }

    public void setWuliaodata(ArrayList<LayoutDABean> wuliaodata) {
        this.wuliaodata = wuliaodata;
    }
}
