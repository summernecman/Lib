package com.siweisoft.service.bean;

//by summer on 17-09-01.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;

public class TipBean extends BaseBean {

    private int position;

    private String tip;

    private int num;

    private boolean select;

    public TipBean() {
    }

    public TipBean(int position, String tip, int num) {
        this.position = position;
        this.tip = tip;
        this.num = num;
    }

    @Bindable
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Bindable
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Bindable
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Bindable
    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "TipBean{" +
                "position=" + position +
                ", tip='" + tip + '\'' +
                ", num=" + num +
                ", select=" + select +
                '}';
    }
}
