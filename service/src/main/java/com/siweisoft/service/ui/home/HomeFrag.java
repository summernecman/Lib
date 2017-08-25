package com.siweisoft.service.ui.home;

//by summer on 17-08-23.

import com.android.lib.base.fragment.BaseUIFrag;

public class HomeFrag extends BaseUIFrag<HomeUIOpe, HomeDAOpe> {

    @Override
    public void doThing() {
        getP().getU().initViewPager(getActivity().getSupportFragmentManager(), getP().getD().getFragment());
    }
}
