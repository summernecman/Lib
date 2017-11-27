package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class SecRender extends BaseRender {

    protected int sec = 0;

    protected float radious = 0;

    public SecRender(Context context) {
        super(context);
        paint.setColor(Color.RED);
        radious = (sw > sh / 2 ? sh / 4 : sw / 2);
    }

    public void sec() {
        sec++;
    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        //LogUtil.E("myDraw" + System.currentTimeMillis());
        canvas.save();
        //canvas.drawLine(sw/2,sh/2,getSx(),getSy(),paint);
        canvas.drawCircle(getSx(), getSy(), mw * 5, paint);
        canvas.restore();
    }

    public float getSx() {
        return (float) (getInnerALength() * Math.cos(Math.toRadians(getDegree())) + sw / 2);
    }

    public float getSy() {
        return (float) (getInnerBLength() * Math.sin(Math.toRadians(getDegree())) + sh / 2);
    }

    public float getInnerALength() {
        return sw / 2 - 50 * mw;
    }

    public float getInnerBLength() {
        return sh / 2 - 50 * mw;
    }


    public int getDegree() {
        return 6 * sec;
    }
}
