package com.summer.desktop.module.home.main;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.desktop.module.app.AppItemsFrag;
import com.summer.desktop.module.day.DayFrag;
import com.summer.desktop.module.note.main.NoteMainFrag;

import java.util.ArrayList;

public class HomeDAOpe extends BaseDAOpe {


    public HomeDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DayFrag());
        fragments.add(new AppItemsFrag());
        fragments.add(new NoteMainFrag());
        return fragments;
    }
}
