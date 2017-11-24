package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.summer.time.ui.main.thing.ThingFrag;

import java.util.ArrayList;

public class MainDAOpe extends BaseDAOpe {

    public MainDAOpe(Context context) {
        super(context);
    }

    public ArrayList<BaseUIFrag> getFrags() {
        ArrayList<BaseUIFrag> fragments = new ArrayList<>();
        fragments.add(new ThingFrag());
        fragments.add(new ThingFrag());
        return fragments;
    }
}
