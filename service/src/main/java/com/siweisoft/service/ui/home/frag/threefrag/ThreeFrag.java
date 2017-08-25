package com.siweisoft.service.ui.home.frag.threefrag;

//by summer on 17-08-25.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.usercenter.UserCenterFrag;

public class ThreeFrag extends BaseUIFrag<ThreeUIOpe, ThreeDAOpe> {
    @Override
    public void doThing() {
        FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new UserCenterFrag());
    }
}
