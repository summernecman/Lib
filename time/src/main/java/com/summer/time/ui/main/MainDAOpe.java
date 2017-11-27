package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.summer.time.ui.main.note.NoteFrag;
import com.summer.time.ui.main.thing.ThingFrag;

import java.util.ArrayList;

public class MainDAOpe extends BaseDAOpe {

    ArrayList<BaseUIFrag> fragments = new ArrayList<>();

    public MainDAOpe(Context context) {
        super(context);
    }

    public ArrayList<BaseUIFrag> initFrags() {
        fragments.clear();
        fragments.add(new ThingFrag());
        fragments.add(new NoteFrag());
        return fragments;
    }

    public ArrayList<BaseUIFrag> getFragments() {
        return fragments;
    }
}
