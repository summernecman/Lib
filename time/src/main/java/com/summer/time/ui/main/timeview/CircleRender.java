package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.android.lib.util.ScreenUtil;
import com.summer.time.R;

public class CircleRender implements DrawI {

    private Paint paint = new Paint();

    private float cx = ScreenUtil.w / 2;

    private float cy = ScreenUtil.h / 2;

    private float radious = 0;

    private int color = Color.BLACK;

    public CircleRender(Context context) {
        color = context.getResources().getColor(R.color.color_purple_300);
        paint.setColor(color);
        radious = (ScreenUtil.w > ScreenUtil.h / 2 ? ScreenUtil.h / 4 : ScreenUtil.w / 2);
        cy = ScreenUtil.h / 2 - radious;
    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        canvas.drawCircle(cx, cy, radious, paint);
    }
}
