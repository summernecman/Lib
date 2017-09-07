package com.siweisoft.service.ui.home.frag.twofrag;

//by summer on 17-08-25.

import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.onlinelist.OnLineListFrag;

public class TwoFrag extends BaseServerFrag<TwoUIOpe, TwoDAOpe> {
    @Override
    public void doThing() {
        FragmentUtil2.getInstance().addNoAnim(activity, Value.ROOTID_TWO, new OnLineListFrag());
    }
}
