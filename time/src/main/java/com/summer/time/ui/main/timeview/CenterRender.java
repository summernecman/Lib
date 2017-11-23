package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class CenterRender extends BaseRender {

    protected float centerRadious;


    public CenterRender(Context context) {
        super(context);
        centerRadious = 5 * mw;
        paint.setColor(Color.RED);
    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        canvas.drawCircle(sw / 2, sh / 2, centerRadious, paint);
    }
}
