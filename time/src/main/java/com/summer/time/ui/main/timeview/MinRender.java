package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Color;

import com.android.lib.util.data.DateFormatUtil;

public class MinRender extends HourRender {


    public MinRender(Context context) {
        super(context);
    }

    protected void init0() {
        all = 60;
        textsize = mw * 9;
        radious = 30;
    }

    protected void init2() {
        for (int i = 0; i < degrees.size(); i++) {
            if (i > 15 && i <= 45) {
                degrees.set(i, degrees.get(i) + 180);
            }
            if (i == 15) {
                degrees.set(i, 180f);
            }
            if (i == 45) {
                degrees.set(i, 0f);
            }
        }
    }


    protected float getDegree(int i) {
        return 360 * i / 60f;
    }

    protected float getInnerALength() {
        return getOutALength() - 10 * mw;
    }

    protected float getInnerBLength() {
        return getOutBLength() - 10 * mw;
    }

    protected float getOutALength() {
        return sw / 2 - radious * mw;
    }

    protected float getOutBLength() {
        return sh / 2 - radious * mw;
    }

    protected float[] getCenterRotate(int i) {
        return new float[]{(float) (hours.get(i)[2]), (float) (hours.get(i)[3])};
    }

    public void now(int i) {
        if (getPos(DateFormatUtil.getMin()) == i) {
            paint.setColor(Color.RED);
            paint.setTextSize(textsize * 2);
        } else {
            paint.setColor(Color.BLACK);
            paint.setTextSize(textsize);
        }
    }

    public int getPos(int min) {
        int i = min - 15;
        if (i < 0) {
            i += 60;
        }
        return i % 60;
    }

    protected String getText(int i) {
        i = (i + 15) % 60;
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

}
