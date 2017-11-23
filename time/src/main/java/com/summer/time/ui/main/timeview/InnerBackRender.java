package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;

import com.android.lib.util.ScreenUtil;
import com.summer.time.R;

public class InnerBackRender extends HoursBackRender {

    private Path path = new Path();

    private double radious = 0;

    public InnerBackRender(Context context) {
        super(context);
        paint.setColor(context.getResources().getColor(R.color.color_blue_300));
    }


    @Override
    public RectF getRectF() {
        return new RectF(50 * mw, 50 * mw, ScreenUtil.w - 50 * mw, ScreenUtil.h - 50 * mw);
    }
}
