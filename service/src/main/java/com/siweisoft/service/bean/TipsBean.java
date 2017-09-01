package com.siweisoft.service.bean;

//by summer on 17-09-01.

import com.android.lib.bean.BaseBean;

import java.util.ArrayList;

public class TipsBean extends BaseBean {

    ArrayList<TipBean> tipBeen = new ArrayList<>();

    public ArrayList<TipBean> getTipBeen() {
        return tipBeen;
    }

    public void setTipBeen(ArrayList<TipBean> tipBeen) {
        this.tipBeen = tipBeen;
    }
}
