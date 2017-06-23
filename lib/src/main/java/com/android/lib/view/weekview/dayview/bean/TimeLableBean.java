package com.android.lib.view.weekview.dayview.bean;

import com.android.lib.bean.resbean.ResultResBean;

/**
 * Created by ${viwmox} on 2016-11-04.
 */
public class TimeLableBean extends ResultResBean {

    public float textSize;

    public int textColor;

    public String[] txt;

    public int width;

    public int height;

    public int getEachHeigh() {
        return (int) ((float) height) / txt.length;
    }


}
