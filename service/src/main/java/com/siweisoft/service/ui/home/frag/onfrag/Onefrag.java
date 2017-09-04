package com.siweisoft.service.ui.home.frag.onfrag;

//by summer on 17-08-25.

import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.history.HistoryFrag;

public class Onefrag extends BaseServerFrag<OneUIOpe, OneDAOpe> {

    @Override
    public void doThing() {
        FragmentUtil2.getInstance().addNoAnim(activity, Value.ROOTID_ONE, new HistoryFrag());
    }
}
