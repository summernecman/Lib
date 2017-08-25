package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.videorecord.VideoRecordFrag;

public class HistoryFrag extends BaseUIFrag<HistoryUIOpe, HistoryDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        getP().getU().initList(getP().getD().getData(), this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_ONE, new VideoRecordFrag());
                break;
        }
    }
}
