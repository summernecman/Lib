package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.android.lib.util.ScreenUtil;

public class BackRender implements DrawI {

    private int color = Color.BLACK;

    private Paint paint = new Paint();

    public BackRender(Context context) {
        //color = context.getResources().getColor(R.color.color_blue_300);
    }


    @Override
    public void onTimeDraw(Canvas canvas) {
        paint.setColor(color);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawRect(0, 0, ScreenUtil.w, ScreenUtil.h, paint);
    }
}
