package com.siweisoft.service.ui.home;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.ui.history.HistoryFrag;
import com.siweisoft.service.ui.user.onlinelist.OnLineListFrag;
import com.siweisoft.service.ui.user.usercenter.UserCenterFrag;

import java.util.ArrayList;

public class HomeDAOpe extends BaseDAOpe {


    public HomeDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HistoryFrag());
        fragments.add(new OnLineListFrag());
        fragments.add(new UserCenterFrag());
        return fragments;
    }
}
