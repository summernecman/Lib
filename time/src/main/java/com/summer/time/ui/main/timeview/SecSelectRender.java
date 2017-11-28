package com.summer.time.ui.main.timeview;

//by summer on 2017-11-28.

import android.content.Context;
import android.view.MotionEvent;

import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;

public class SecSelectRender extends SecRender {

    private float downx, downy;
    private float movex, movey;
    private boolean isin;

    public SecSelectRender(Context context) {
        super(context);
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getDegree() {
        return getDegree(movex, movey);
    }


    public int getDegree(float x, float y) {
        double degree = 0;
        double w = x - ScreenUtil.w / 2d;
        double h = y - ScreenUtil.h / 2d;
        double r = Math.pow(Math.pow(w, 2) + Math.pow(h, 2), 1 / 2d);
        double degreecos = Math.toDegrees(Math.acos(w / r));
        double degreesin = Math.toDegrees(Math.asin(h / r));
        LogUtil.E(degreecos + ":::" + degreesin);
        if (h > 0) {
            degree = degreecos;
        } else {
            if (w > 0) {
                degree = 360 + degreesin;
            } else {
                degree = 180 - degreesin;
            }
        }
        LogUtil.E(degree);
        int d = (int) degree;
        int dd = (6 * (d / 6));
        return dd;
    }

    public boolean ontouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                isin = isIn(downx, getSx(), downy, getSy());
                break;
            case MotionEvent.ACTION_MOVE:
                movex = event.getX();
                movey = event.getY();
                if (isin) {

                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public boolean isIn(float x1, float x2, float y1, float y2) {
        if ((Math.abs(x1 - x2) < radious * 2) && (Math.abs(y1 - y2) < radious * 2)) {
            return true;
        }
        return false;
    }
}
