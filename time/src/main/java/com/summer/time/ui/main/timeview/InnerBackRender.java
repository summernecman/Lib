package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;

import com.android.lib.util.ScreenUtil;
import com.summer.time.R;

public class InnerBackRender extends HoursBackRender {

    private Path path = new Path();


    public InnerBackRender(Context context) {
        super(context);
        radious = 60;
        paint.setColor(context.getResources().getColor(R.color.color_blue_300));
    }


    @Override
    public RectF getRectF() {
        return new RectF(radious * mw, radious * mw, ScreenUtil.w - radious * mw, ScreenUtil.h - radious * mw);
    }
}
