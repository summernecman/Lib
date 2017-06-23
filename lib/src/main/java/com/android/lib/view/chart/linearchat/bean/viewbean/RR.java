package com.android.lib.view.chart.linearchat.bean.viewbean;

import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class RR implements Serializable {

    private float cx;

    private float cy;

    private float cr;

    private Paint paint;


    public RR(float cx, float cy, float cr) {
        this.cx = cx;
        this.cy = cy;
        this.cr = cr;
    }

    public float getCr() {
        return cr;
    }

    public void setCr(float cr) {
        this.cr = cr;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public Paint getPaint() {
        if (paint == null) {
            paint = new Paint();
        }
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
