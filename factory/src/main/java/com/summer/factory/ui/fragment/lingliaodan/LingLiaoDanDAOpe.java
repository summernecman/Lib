package com.summer.factory.ui.fragment.lingliaodan;

//by summer on 2017-07-18.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.factory.bean.da.LayoutDABean;

import java.util.ArrayList;

public class LingLiaoDanDAOpe extends BaseDAOpe {

    ArrayList<LayoutDABean> data;

    public LingLiaoDanDAOpe(Context context) {
        super(context);
    }

    public ArrayList<LayoutDABean> getData() {
        return data;
    }

    public void setData(ArrayList<LayoutDABean> data) {
        this.data = data;
    }
}
