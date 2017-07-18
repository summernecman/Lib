package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.WuLiaoItemDABean;

import java.util.ArrayList;

public class WuLiaoDAOpe extends BaseDAOpe {

    ArrayList<WuLiaoItemDABean> wuliaodata;

    public WuLiaoDAOpe(Context context) {
        super(context);
    }

    public ArrayList<WuLiaoItemDABean> getWuliaodata() {
        return wuliaodata;
    }

    public void setWuliaodata(ArrayList<WuLiaoItemDABean> wuliaodata) {
        this.wuliaodata = wuliaodata;
    }
}
