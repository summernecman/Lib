package com.summer.desktop.bean.dabean;

import com.summer.lib.bean.databean.BaseDABean;

/**
 * Created by summer on 2017/5/25 23:45.
 */

public class TxtNote extends BaseDABean {

    private String txt = "";

    private float size;

    private int color;

    public TxtNote(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
