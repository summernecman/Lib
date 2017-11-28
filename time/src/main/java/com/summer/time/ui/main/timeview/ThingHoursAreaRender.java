package com.summer.time.ui.main.timeview;

//by summer on 2017-11-23.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.android.lib.util.ScreenUtil;
import com.summer.time.R;

public class ThingHoursAreaRender extends OneHoursAreaRender {

    protected int sth, sm, eh, em;


    public ThingHoursAreaRender(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getStrkeWidth());
        color = context.getResources().getColor(R.color.color_light_green_500);
        paint.setColor(color);
        paint.setTextSize(ScreenUtil.mw * 10);
    }


    @Override
    public RectF getRectF() {
        return new RectF(getStrkeWidth() / 2, getStrkeWidth() / 2, ScreenUtil.w - getStrkeWidth() / 2, ScreenUtil.h - getStrkeWidth() / 2);
    }

    public float getStrkeWidth() {
        return mw * 15;
    }


    public double getX(float a, float dp, int h, int min) {
        return (a - dp * mw) * Math.cos(Math.toRadians(getDegree(h, min))) + getXL();
    }

    public double getY(float b, float dp, int h, int min) {
        return -(b - dp * mw) * Math.sin(Math.toRadians(-getDegree(h, min))) + getYL();
    }


    public float getA(float dp) {
        RectF rectF = getRectF();
        return (rectF.right - rectF.left) / 2f + dp;
    }

    public float getB(float dp) {
        RectF rectF = getRectF();
        return (rectF.bottom - rectF.top) / 2f + dp;
    }


    public String getAreaIV(float[] f) {
        float x = f[0] - sw / 2;
        float y = f[1] - sh / 2;
        if (x > 0) {
            if (y > 0) {
                return "II";
            } else {
                return "I";
            }
        } else {
            if (y > 0) {
                return "III";
            } else {
                return "IV";
            }
        }
    }

    public String getNextAreaIV(String str) {
        switch (str) {
            case "I":
                return "IV";
            case "II":
                return "I";
            case "III":
                return "II";
            case "IV":
                return "III";

        }
        return "";
    }

    public float[] getAreaIVXY(String area) {
        switch (area) {
            case "I":
                return new float[]{sw, 0};
            case "II":
                return new float[]{sw, sh};
            case "III":
                return new float[]{0, sh};
            case "IV":
                return new float[]{0, 0};

        }
        return new float[]{0, 0};
    }


    public void setTime(int sth, int sm, int eh, int em) {
        this.sth = sth;
        this.sm = sm;
        this.eh = eh;
        this.em = em;
        initPath();
    }

    float[] startxyout = new float[]{0f, 0f}, startxyin = new float[]{0f, 0f}, endxyin = new float[]{0f, 0f}, endxyout = new float[]{0f, 0f};
    protected void initPath() {
        MyPath path = new MyPath();
        path.moveTo(sw / 2, sh / 2);
        startxyout = new float[]{(float) getX(getA(), -getStrkeWidth() / 2, sth, sm), (float) getY(getB(), -getStrkeWidth() / 2, sth, sm)};
        startxyin = new float[]{(float) getX(getA(), getStrkeWidth() / 2, sth, sm), (float) getY(getB(), getStrkeWidth() / 2, sth, sm)};
        endxyin = new float[]{(float) getX(getA(), getStrkeWidth() / 2, eh, em), (float) getY(getB(), getStrkeWidth() / 2, eh, em)};
        endxyout = new float[]{(float) getX(getA(), -getStrkeWidth() / 2, eh, em), (float) getY(getB(), -getStrkeWidth() / 2, eh, em)};
        path.lineTo(endxyin[0], endxyin[1]);
        path.lineTo(endxyout[0], endxyout[1]);
        float[] areaxy = getAreaIVXY(getAreaIV(endxyin));
        path.lineTo(areaxy[0], areaxy[1]);
        String area = getAreaIV(endxyin);
        String nextareaiv = area;
        if ((getAreaIV(startxyout).equals(nextareaiv)) && getRule(new int[]{sth, eh})) {
            nextareaiv = getNextAreaIV(nextareaiv);
        }
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
        this.path = path;
    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        canvas.save();
        paint.setStrokeWidth(getStrkeWidth());
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        initPath();
        canvas.clipPath(path);
        canvas.drawOval(getRectF(), paint);
        canvas.restore();
        canvas.save();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        paint.setColor(Color.RED);
        double degree = getDegree(sth, sm);
        float[] f = getCenterRotate(startxyin, startxyout);
        canvas.rotate((float) degree, f[0], f[1]);
        canvas.translate(0, paint.getTextSize() / 2);
        canvas.drawText(sm + "", f[0], f[1], paint);
        canvas.restore();

        canvas.save();
        double degree2 = getDegree(eh, em);
        ;
        float[] f2 = getCenterRotate(endxyin, endxyout);
        canvas.rotate((float) degree2, f2[0], f2[1]);
        canvas.translate(0, paint.getTextSize() / 2);
        canvas.drawText(em + "", f2[0], f2[1], paint);
        canvas.restore();
    }

    public float[] getCenterRotate(float[] s, float[] e) {
        return new float[]{(s[0] + e[0]) / 2f, (s[1] + e[1]) / 2f};
    }

    private boolean getRule(int[] m) {
        int max = 60 / 2;
        return ((m[0] > m[1]) && (Math.abs(m[0] - m[1]) < max)) || ((m[0] < m[1]) && (Math.abs(m[0] - m[1]) > max));
    }

}
