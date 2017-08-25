package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import com.android.lib.base.fragment.BaseUIFrag;

public class UserInfoFrag extends BaseUIFrag<UserInfoUIOpe, UserInfoDAOpe> {

    @Override
    public void doThing() {
        getP().getU().initTips(getP().getD().getData());
        getP().getU().initRemarks(getP().getD().getRemarks());
    }
}
