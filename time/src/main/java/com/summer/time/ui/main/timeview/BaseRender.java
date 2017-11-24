package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Paint;

import com.android.lib.util.ScreenUtil;

public abstract class BaseRender implements DrawI {

    protected Paint paint = new Paint();

    protected float sw = ScreenUtil.w;

    protected float sh = ScreenUtil.h;

    protected float mw = ScreenUtil.mw;

    protected float radious = 0;


    public BaseRender(Context context) {
        paint.setAntiAlias(true);
    }
}
