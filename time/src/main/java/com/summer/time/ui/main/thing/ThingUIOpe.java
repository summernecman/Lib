package com.summer.time.ui.main.thing;

//by summer on 2017-11-24.

import android.content.Context;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.RandomUtil;
import com.summer.time.R;
import com.summer.time.databinding.FragThingBinding;

public class ThingUIOpe extends BaseUIOpe<FragThingBinding> {


    private int nowposition = 0;

    public ThingUIOpe(Context context) {
        super(context);
    }

    public void initThing() {
        bind.thing.initData();
        bind.thing.setOnScroll(new OnFinishListener() {
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
                int sm = RandomUtil.getInstance().nextInt(59);
                int eh = sh + RandomUtil.getInstance().nextInt(23);
                if (eh > 24) {
                    eh -= 24;
                }
                int em = RandomUtil.getInstance().nextInt(59);
                //bind.time.UpdateThingArea(sh, sm, eh, em);
            }
        });
    }
}
