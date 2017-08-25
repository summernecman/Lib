package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.ui.home.frag.onfrag.Onefrag;
import com.siweisoft.service.ui.home.frag.threefrag.ThreeFrag;
import com.siweisoft.service.ui.home.frag.twofrag.TwoFrag;

import java.util.ArrayList;

public class MainDAOpe extends BaseDAOpe {


    public MainDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Onefrag());
        fragments.add(new TwoFrag());
        fragments.add(new ThreeFrag());
        return fragments;
    }
}
