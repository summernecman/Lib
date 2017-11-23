package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.content.Context;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.time.R;
import com.summer.time.databinding.ActMainBinding;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {

    private int nowposition = 0;

    public MainUIOpe(Context context) {
        super(context);
        initTime();
        initThing();
    }

    public void initTime() {

    }

    public void initThing() {
        bind.recycle.initData();
        bind.recycle.setOnScroll(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if (1 == 2) {
                    return;
                }
                View v = (View) o;
                if (nowposition == (int) v.getTag(R.id.data)) {
                    return;
                }
                nowposition = (int) v.getTag(R.id.data);
                int sh = nowposition % 24;
                int sm = 10;
                int eh = sh + 2;
                if (eh > 24) {
                    eh = 24;
                }
                int em = 10;
                if (em > 60) {
                    em = 60;
                }
                bind.time.UpdateThingArea(sh, sm, eh, em);
            }
        });
    }

}
