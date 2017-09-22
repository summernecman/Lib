package com.siweisoft.service.ui.main;

//by summer on 17-09-22.

import com.android.lib.util.LogUtil;
import com.hyphenate.EMMultiDeviceListener;

import java.util.List;

public class MyMultiDeviceListener implements EMMultiDeviceListener {
    @Override
    public void onContactEvent(int event, String target, String ext) {
        LogUtil.E(event + "" + target + "" + event);
    }

    @Override
    public void onGroupEvent(int event, String target, List<String> usernames) {
        LogUtil.E(event + "" + target + "" + event + usernames);
    }
}
