package com.summer.lib.view.chart.linearchat.bean.viewbean;

import android.graphics.Paint;
import android.graphics.RectF;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-11-28.
 */
public class ChartItemBean implements Serializable {

    private Paint paint;

    private RectF rectF;


    public Paint getPaint() {
        if (paint == null) {
            paint = new Paint();
        }
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }


    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }
}
