package com.summer.time.ui.main.thing;

//by summer on 2017-11-24.

import android.content.Context;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.ScreenUtil;
import com.summer.time.R;
import com.summer.time.databinding.FragThingBinding;
import com.summer.time.ui.main.thingview.ThingBean;

import java.util.ArrayList;

public class ThingUIOpe extends BaseUIOpe<FragThingBinding> {


    private int nowposition = 0;

    ViewListener listener;

    public ThingUIOpe(Context context) {
        super(context);
    }

    public void initThing(ArrayList<ThingBean> data, ViewListener listener) {
        this.listener = listener;
        bind.thing.initData(data, listener);
    }

    public void initThing(ArrayList<ThingBean> data) {
        bind.thing.initData(data, listener);
    }

    public void refreshThing() {
        bind.thing.getAdapter().notifyDataSetChanged();
        bind.thing.scrollToPosition(0);
    }

    public void setOnScroll(final OnFinishListener onFinishListener) {
        if (onFinishListener == null) {
            return;
        }
        bind.thing.setOnScroll(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                View v = (View) o;
                ThingBean thingBean = (ThingBean) v.getTag(R.id.data);
                if (thingBean.getPos() == nowposition) {
                    return;
                }
                nowposition = thingBean.getPos();
                onFinishListener.onFinish(thingBean);
            }
        });
    }

    public void scrollToPos(int pos) {
        if (pos == -1) {
            return;
        }
        bind.thing.scrollToPosition(pos);
        bind.thing.scrollBy(0, (int) (25 * ScreenUtil.mw));
    }
}
