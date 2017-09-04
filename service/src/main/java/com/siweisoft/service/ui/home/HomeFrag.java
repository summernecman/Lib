package com.siweisoft.service.ui.home;

//by summer on 17-08-23.

import com.siweisoft.service.base.BaseServerFrag;

public class HomeFrag extends BaseServerFrag<HomeUIOpe, HomeDAOpe> {

    @Override
    public void doThing() {
        getP().getU().initViewPager(getActivity().getSupportFragmentManager(), getP().getD().getFragment());
    }
}
