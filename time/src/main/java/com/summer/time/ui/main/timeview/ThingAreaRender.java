package com.summer.time.ui.main.timeview;

//by summer on 2017-11-23.

import android.content.Context;

public class ThingAreaRender extends OneHoursAreaRender {

    protected float startAngel;

    protected float endAngel;

    public ThingAreaRender(Context context) {
        super(context);
    }

    protected void initPath() {
        path.reset();
        path.moveTo((float) getInnX(startAngel), (float) getInnY(startAngel));
        path.lineTo((float) get2X(startAngel), (float) get2Y(startAngel));
        path.lineTo((float) get2X(endAngel), (float) get2Y(endAngel));
        path.lineTo((float) getInnX(endAngel), (float) getInnY(endAngel));
        path.close();
    }

    public float getStartAngel() {
        return startAngel;
    }

    public void setStartAngel(int hours, int min) {
        startAngel = (hours * 360 / 24f) + min * ((360 / 24f) / 60f);
    }

    public float getEndAngel() {
        return endAngel;
    }

    public void setEndAngel(int hours, int min) {
        endAngel = (hours * 360 / 24f) + min * ((360 / 24f) / 60f);
    }
}
