package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import com.android.lib.base.fragment.BaseUIFrag;

public class UserCenterFrag extends BaseUIFrag<UserCenterUIOpe, UserCenterDAOpe> {
    @Override
    public void doThing() {
        getP().getU().initTips(getP().getD().userInfoDAOpe.getData());
    }

}
