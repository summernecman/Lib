package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.android.lib.util.ScreenUtil;

public class SinRender implements DrawI {

    private Paint paint = new Paint();

    private Path path = new Path();


    private double radious = 0;


    public SinRender(Context context) {
        radious = (ScreenUtil.w > ScreenUtil.h / 2 ? ScreenUtil.h / 4 : ScreenUtil.w / 2);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(0, 0);
        for (int i = 0; i < 4 * radious; i++) {
            double x = i;
            double y = radious * Math.sin(x * Math.PI / (2 * radious));
            path.lineTo((float) x, (float) y);
        }


    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(90, -1, -1);
        canvas.translate(0, -ScreenUtil.w / 2 - 2);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.restore();
    }
}
