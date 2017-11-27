package com.summer.time.ui.main.timeview;

//by summer on 2017-11-23.

import android.content.Context;

import com.android.lib.util.data.DateFormatUtil;
import com.summer.time.R;

public class OneHoursAreaRender extends HoursAreaRender {

    public OneHoursAreaRender(Context context) {
        super(context);
        color = context.getResources().getColor(R.color.color_brown_300);
        paint.setColor(color);
        radious = 25;
    }


    protected void initPath() {
        path.reset();
        path.moveTo((float) getInnX(), (float) getInnY());
        path.lineTo((float) get2X(), (float) get2Y());
        path.lineTo((float) get2X(getDegree()), (float) get2Y(getDegree()));
        path.lineTo((float) getInnX(getDegree()), (float) getInnY(getDegree()));
        path.close();
    }

    public float getDegree() {
        return (DateFormatUtil.getHours() * 360 / 24f) + DateFormatUtil.getMin() * ((360 / 24f) / 60f);
    }

    public float getDegree(int h, int m) {
        return (h * 360 / 24f) + m * ((360 / 24f) / 60f);
    }




    public double get2X() {
        return (getA() + mw * radious) * Math.cos(Math.toRadians(getDegree(getPos()))) + getXL();
    }

    public double get2Y() {
        return (getB() + mw * radious) * Math.sin(Math.toRadians(getDegree(getPos()))) + getYL();
    }

    public double get2X(float degree) {
        return (getA() + mw * radious) * Math.cos(Math.toRadians(degree)) + getXL();
    }

    public double get2Y(float degree) {
        return (getB() + mw * radious) * Math.sin(Math.toRadians(degree)) + getYL();
    }
}
