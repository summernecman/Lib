package com.summer.time.ui.main.timeview;

//by summer on 2017-11-24.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.android.lib.util.ScreenUtil;
import com.android.lib.util.data.DateFormatUtil;

import java.util.ArrayList;

public class ThingMinAreaRender extends ThingHoursAreaRender {

    private ArrayList<MyPath> paths = new ArrayList<>();

    int[] colors;

    public ThingMinAreaRender(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getStrkeWidth());
        colors = new int[]{Color.RED, Color.BLUE, Color.GREEN};
        radious = 25;
    }

    public float getStrkeWidth() {
        return mw * 4;
    }

    @Override
    public RectF getRectF() {
        return new RectF(radious * mw + getStrkeWidth() / 2, radious * mw + getStrkeWidth() / 2, ScreenUtil.w - getStrkeWidth() / 2 - radious * mw, ScreenUtil.h - getStrkeWidth() / 2 - radious * mw);
    }

    public RectF getRectF(int level) {
        return new RectF(level * getStrkeWidth() + radious * mw + getStrkeWidth() / 2, radious * mw + getStrkeWidth() / 2 + level * getStrkeWidth(), ScreenUtil.w - getStrkeWidth() / 2 - radious * mw - level * getStrkeWidth(), ScreenUtil.h - getStrkeWidth() / 2 - radious * mw - level * getStrkeWidth());
    }


    protected void initPath() {
        paths.clear();
        ArrayList<int[]> floats = DateFormatUtil.getTimeLevels(sth, sm, eh, em);
        switch (floats.size()) {
            case 0:
                break;
            case 1:
                paths.add(getClipPath(floats.get(0)));
                break;
            case 2:
                paths.add(getClipPath(floats.get(0)));
                paths.add(getClipPath(floats.get(1)));
                break;
            default:
                paths.add(getClipPath(floats.get(0)));
                for (int i = 0; i < floats.size() - 2; i++) {
                    //paths.add(new MyPath(false));
                }
                paths.add(getClipPath(floats.get(floats.size() - 1)));
                break;
        }
    }


    @Override
    public void onTimeDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            canvas.save();
            if (paths.get(i).isUsed()) {
                canvas.clipPath(paths.get(i));
            }
            paint.setColor(colors[i % colors.length]);
            canvas.drawOval(getRectF(i), paint);
            canvas.restore();
        }
    }

    public MyPath getClipPath(int[] m) {
        MyPath path = new MyPath();
        path.moveTo(sw / 2, sh / 2);
        float[] startxyout = new float[]{(float) getX(getA(), -getStrkeWidth() / 2, m[0]), (float) getY(getB(), -getStrkeWidth() / 2, m[0])};
        float[] startxyin = new float[]{(float) getX(getA(), getStrkeWidth() / 2, m[0]), (float) getY(getB(), getStrkeWidth() / 2, m[0])};
        float[] endxyin = new float[]{(float) getX(getA(), getStrkeWidth() / 2, m[1]), (float) getY(getB(), getStrkeWidth() / 2, m[1])};
        float[] endxyout = new float[]{(float) getX(getA(), -getStrkeWidth() / 2, m[1]), (float) getY(getB(), -getStrkeWidth() / 2, m[1])};
        path.lineTo(endxyin[0], endxyin[1]);
        path.lineTo(endxyout[0], endxyout[1]);
        float[] areaxy = getAreaIVXY(getAreaIV(endxyin));
        path.lineTo(areaxy[0], areaxy[1]);
        String area = getAreaIV(endxyin);
        String nextareaiv = area;
        while (!getAreaIV(startxyout).equals(nextareaiv)) {
            float[] newxy = getAreaIVXY(nextareaiv);
            path.lineTo(newxy[0], newxy[1]);
            nextareaiv = getNextAreaIV(nextareaiv);
        }
        float[] newxy = getAreaIVXY(nextareaiv);
        path.lineTo(newxy[0], newxy[1]);
        path.lineTo(startxyout[0], startxyout[1]);
        path.lineTo(startxyin[0], startxyin[1]);
        path.lineTo(sw / 2, sh / 2);
        path.close();
        return path;
    }


    protected int getPos() {
        return DateFormatUtil.getMin();
    }

    protected float getDegree(int pos) {
        return 360 * pos / 60 - 90;
    }


    public double getX(float a, float dp, int min) {
        return (a - dp * mw) * Math.cos(Math.toRadians(getDegree(min))) + getXL();
    }

    public double getY(float b, float dp, int min) {
        return -(b - dp * mw) * Math.sin(Math.toRadians(-getDegree(min))) + getYL();
    }


    public float getA(float dp) {
        RectF rectF = getRectF();
        return (rectF.right - rectF.left) / 2f + dp;
    }

    public float getB(float dp) {
        RectF rectF = getRectF();
        return (rectF.bottom - rectF.top) / 2f + dp;
    }


}
