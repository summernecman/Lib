package com.summer.time.ui.main.timeview;

//by summer on 2017-11-23.

import android.content.Context;
import android.graphics.Canvas;

import com.android.lib.util.data.DateFormatUtil;
import com.summer.time.R;

public class MinAreaRender extends MinBackRender {


    public MinAreaRender(Context context) {
        super(context);
        color = context.getResources().getColor(R.color.color_teal_700);
        paint.setColor(color);
    }


    protected int getPos() {
        return DateFormatUtil.getMin();
    }

    protected float getDegree(int pos) {
        return 360 * pos / 60 - 90;
    }

    public double getY() {
        return -getB() * Math.sin(Math.toRadians(-getDegree(getPos()))) + getYL();
    }


    @Override
    public void onTimeDraw(Canvas canvas) {
        canvas.save();
        paint.setColor(color);
        initPath();
        canvas.clipPath(path);
        canvas.drawOval(getRectF(), paint);
        canvas.restore();
    }


    protected void initPath() {
        path.reset();
        path.moveTo(sw / 2, 0);
        path.lineTo(sw / 2, sh / 2);
        path.lineTo((float) getInnX(), (float) getInnY());
        path.lineTo((float) getX(), (float) getY());
        int level = getPos() / 15;
        if (level >= 3) {
            path.lineTo(0, 0);
        }
        if (level >= 2) {
            path.lineTo(0, sh);
        }
        if (level >= 1) {
            path.lineTo(sw, sh);
        }
        if (level >= 0) {
            path.lineTo(sw, 0);
        }
        path.close();
    }


    public double getInnX() {
        return (getA() - mw * 50) * Math.cos(Math.toRadians(getDegree(getPos()))) + getXL();
    }

    public double getInnY() {
        return (getB() - mw * 50) * Math.sin(Math.toRadians(getDegree(getPos()))) + getYL();
    }

}
