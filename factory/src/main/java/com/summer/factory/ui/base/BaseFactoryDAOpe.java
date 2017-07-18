package com.summer.factory.ui.base;

//by summer on 2017-07-17.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class BaseFactoryDAOpe extends BaseDAOpe {

    ArrayList<Fragment> fragments;

    public BaseFactoryDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }
}
