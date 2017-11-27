package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.summer.time.ui.main.thing.ThingFrag;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> {

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initTime(getP().getD().initFrags());
        getP().getU().updateTime((ThingFrag) getP().getD().getFragments().get(0));

    }
}
