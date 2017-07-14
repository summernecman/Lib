package com.siweisoft.service.ui.main;

//by summer on 2017-07-07.

import com.android.lib.util.LogUtil;
import com.bairuitech.anychat.AnyChatRecordEvent;

public class AnyChatRecordEventImp implements AnyChatRecordEvent {
    @Override
    public void OnAnyChatRecordEvent(int dwUserId, int dwErrorCode, String lpFileName, int dwElapse, int dwFlags, int dwParam, String lpUserStr) {
        LogUtil.E("OnAnyChatRecordEvent:" + dwUserId + "-0-" + dwErrorCode + "-0-" + lpFileName + "-0-" + dwElapse + "-0-" + dwFlags + "-0-" + dwParam + "-0-" + lpUserStr);
    }

    @Override
    public void OnAnyChatSnapShotEvent(int dwUserId, int dwErrorCode, String lpFileName, int dwFlags, int dwParam, String lpUserStr) {

    }
}
