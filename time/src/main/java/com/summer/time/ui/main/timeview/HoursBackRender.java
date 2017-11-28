package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

import com.android.lib.util.ScreenUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.summer.time.R;

public class HoursBackRender extends BaseRender implements OvalI {

    protected Path path = new Path();

    protected int color = Color.BLACK;

    protected float radious = 0;

    public HoursBackRender(Context context) {
        super(context);
        color = context.getResources().getColor(R.color.color_light_green_50);
        paint.setColor(color);
        radious = (sw > sh / 2 ? sh / 4 : sw / 2);
    }

    protected int getPos() {
        return DateFormatUtil.getHours();
    }

    protected float getDegree(int pos) {
        return 360 * pos / 24;
    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        canvas.save();
        //paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(getRectF(), paint);
        //paint.setStyle(Paint.Style.FILL);
        canvas.restore();

    }

    @Override
    public RectF getRectF() {
        return new RectF(0, 0, ScreenUtil.w, ScreenUtil.h);
    }

    @Override
    public float getA() {
        RectF rectF = getRectF();
        return (rectF.right - rectF.left) / 2f;
    }

    @Override
    public float getB() {
        RectF rectF = getRectF();
        return (rectF.bottom - rectF.top) / 2f;
    }

    @Override
    public float getXL() {
        return sw / 2;
    }

    @Override
    public float getYL() {
        return sh / 2;
    }

    public double getX() {
        return getA() * Math.cos(Math.toRadians(getDegree(getPos()))) + getXL();
    }

    public double getY() {
        return getB() * Math.sin(Math.toRadians(getDegree(getPos()))) + getYL();
    }

    @Override
    public double getX(float degree) {
        return getA() * Math.cos(Math.toRadians(degree)) + getXL();
    }

    @Override
    public double getY(float degree) {
        return getB() * Math.sin(Math.toRadians(degree)) + getYL();
    }


}
