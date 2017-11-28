package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;

import java.util.ArrayList;

public class HourRender extends BaseRender {

    protected ArrayList<double[]> hours = new ArrayList<>();

    protected ArrayList<Float> degrees = new ArrayList<>();

    protected float textsize = mw * 8;

    protected int all = 24;


    public HourRender(Context context) {
        super(context);
        init0();
        init();
        init2();
    }

    protected void init0() {
        all = 24;
        textsize = mw * 12;
    }

    protected void init() {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textsize);
        paint.setColor(Color.BLACK);
        for (int i = 0; i < all; i++) {
            double[] d = new double[]{(getOutALength() * Math.cos(Math.toRadians(getDegree(i))) + sw / 2), (getOutBLength() * Math.sin(Math.toRadians(getDegree(i))) + sh / 2),
                    (getInnerALength() * Math.cos(Math.toRadians(getDegree(i))) + sw / 2), (getInnerBLength() * Math.sin(Math.toRadians(getDegree(i))) + sh / 2)};
            hours.add(d);
            double degree = Math.toDegrees(Math.PI / 2 + Math.tanh(((double) (d[3] - d[1]) / (d[2] - d[0]))));
            degrees.add((float) degree);
        }
    }

    protected void init2() {
        for (int i = 0; i < degrees.size(); i++) {
            if (i > 6 && i <= 18) {
                degrees.set(i, degrees.get(i) + 180);
            }
            if (i == 6) {
                degrees.set(i, 180f);
            }
            if (i == 18) {
                degrees.set(i, 0f);
            }
        }
    }

    protected float getDegree(int i) {
        return 360 * i / 24f;
    }

    protected float getInnerALength() {
        return sw / 2 - textsize;
    }

    protected float getInnerBLength() {
        return sh / 2 - textsize;
    }

    protected float getOutALength() {
        return sw / 2;
    }

    protected float getOutBLength() {
        return sh / 2;
    }

    protected float[] getCenterRotate(int i) {
        return new float[]{(float) (hours.get(i)[2]), (float) (hours.get(i)[3])};
    }

    @Override
    public void onTimeDraw(Canvas canvas) {

        for (int i = 0; i < hours.size(); i++) {
            canvas.drawLine((float) (hours.get(i)[0]), (float) (hours.get(i)[1]), (float) (hours.get(i)[2]), (float) (hours.get(i)[3]), paint);
            canvas.save();
            canvas.rotate(degrees.get(i), getCenterRotate(i)[0], getCenterRotate(i)[1]);
            canvas.translate(0, textsize / 2);
            now(i);
            canvas.drawText(getText(i), getCenterRotate(i)[0], getCenterRotate(i)[1], paint);
            canvas.restore();
        }
    }

    public void now(int i) {
        if (getPos(DateFormatUtil.getHours()) == i) {
            paint.setColor(Color.RED);
            paint.setTextSize(textsize * 2);
        } else {
            paint.setColor(Color.BLACK);
            paint.setTextSize(textsize);
        }
    }

    protected String getText(int i) {
        return StringUtil.getAddZero(i);
    }

    public int getPos(int hours) {
        return hours;
    }
}
