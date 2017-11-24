package com.summer.time.ui.main.thing;

//by summer on 2017-11-24.

import com.android.lib.base.fragment.BaseUIFrag;

public class ThingFrag extends BaseUIFrag<ThingUIOpe, ThingDAOpe> {

    @Override
    public void doThing() {
        getP().getU().initThing();
    }
}
