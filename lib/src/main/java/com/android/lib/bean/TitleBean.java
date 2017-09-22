package com.android.lib.bean;

//by summer on 17-08-28.

import android.databinding.Bindable;

public class TitleBean extends BaseBean {

    private String leftTxt;

    private String midTxt;

    private String rightTxt;

    private String rightTxt2;

    public TitleBean(String leftTxt, String midTxt, String rightTxt) {
        this.leftTxt = leftTxt;
        this.midTxt = midTxt;
        this.rightTxt = rightTxt;
    }

    public TitleBean(String leftTxt, String midTxt, String rightTxt, String rightTxt2) {
        this.leftTxt = leftTxt;
        this.midTxt = midTxt;
        this.rightTxt = rightTxt;
        this.rightTxt2 = rightTxt2;
    }

    @Bindable
    public String getLeftTxt() {
        return leftTxt;
    }

    public void setLeftTxt(String leftTxt) {
        this.leftTxt = leftTxt;
    }

    @Bindable
    public String getMidTxt() {
        return midTxt;
    }

    public void setMidTxt(String midTxt) {
        this.midTxt = midTxt;
    }

    @Bindable
    public String getRightTxt() {
        return rightTxt;
    }

    public void setRightTxt(String rightTxt) {
        this.rightTxt = rightTxt;
    }

    @Bindable
    public String getRightTxt2() {
        return rightTxt2;
    }

    public void setRightTxt2(String rightTxt2) {
        this.rightTxt2 = rightTxt2;
    }
}
