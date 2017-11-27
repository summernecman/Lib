package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.summer.time.databinding.ActMainBinding;
import com.summer.time.ui.main.thing.ThingFrag;
import com.summer.time.ui.main.timeview.TimeBean;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {

    private TimeBean[] p;

    public MainUIOpe(Context context) {
        super(context);
    }

    public void initTime(final ArrayList<BaseUIFrag> fragments) {
        bind.vpVp.init(fragments);
    }

    public void updateTime(ThingFrag thingFrag) {
        thingFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                TimeBean[] pos = (TimeBean[]) o;
                if (p != null) {
                    if ((p[0].hour == pos[0].hour) && (p[1].hour == pos[1].hour) && (p[0].minute == pos[0].minute) && (p[1].minute == pos[1].minute)) {
                        return;
                    }
                }
                p = pos;
                LogUtil.E(pos[0].hour + ":" + pos[0].minute + "---" + pos[1].hour + ":" + pos[1].minute);
                bind.time.UpdateThingArea(pos[0].hour, pos[0].minute, pos[1].hour, pos[1].minute);
                //bind.time.UpdateThingArea(0,05,22,45);
            }
        });
    }


}
