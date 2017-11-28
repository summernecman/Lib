package com.summer.time.ui.main.thing;

//by summer on 2017-11-24.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;

public class ThingFrag extends BaseUIFrag<ThingUIOpe, ThingDAOpe> implements ViewListener {

    private OnFinishListener onFinishListener;

    private OnFinishListener deleteListener;

    @Override
    public void doThing() {
        getP().getU().initThing(getP().getD().initThings(), this);
        getP().getU().setOnScroll(onFinishListener);
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public void setDeleteListener(OnFinishListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONLONGCLICK:
                deleteListener.onFinish(v);
                break;
        }
    }
}
