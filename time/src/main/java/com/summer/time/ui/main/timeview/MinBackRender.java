package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.RectF;

import com.android.lib.util.ScreenUtil;
import com.summer.time.R;

public class MinBackRender extends HoursBackRender {


    public MinBackRender(Context context) {
        super(context);
        paint.setColor(context.getResources().getColor(R.color.color_purple_100));
    }


    @Override
    public RectF getRectF() {
        return new RectF(30 * mw, 30 * mw, ScreenUtil.w - 30 * mw, ScreenUtil.h - 30 * mw);
    }

    protected float getDegree(int pos) {
        return 360 * pos / 60;
    }
}
