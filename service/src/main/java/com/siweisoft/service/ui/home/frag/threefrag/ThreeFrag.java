package com.siweisoft.service.ui.home.frag.threefrag;

//by summer on 17-08-25.

import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.usercenter.UserCenterFrag;

public class ThreeFrag extends BaseServerFrag<ThreeUIOpe, ThreeDAOpe> {
    @Override
    public void doThing() {
        FragmentUtil2.getInstance().addNoAnim(activity, Value.ROOTID_THREE, new UserCenterFrag());
    }
}
