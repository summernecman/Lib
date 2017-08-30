package com.siweisoft.service.ui.home.frag.twofrag;

//by summer on 17-08-25.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userlist.UserListFrag;

public class TwoFrag extends BaseUIFrag<TwoUIOpe, TwoDAOpe> {
    @Override
    public void doThing() {
        FragmentUtil2.getInstance().addNoAnim(activity, Value.ROOTID_TWO, new UserListFrag());
    }
}