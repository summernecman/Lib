package com.summer.time.ui.main.thing;

//by summer on 2017-11-24.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;

public class ThingFrag extends BaseUIFrag<ThingUIOpe, ThingDAOpe> {

    private OnFinishListener onFinishListener;

    @Override
    public void doThing() {
        getP().getU().initThing();
        getP().getU().setOnScroll(onFinishListener);
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
