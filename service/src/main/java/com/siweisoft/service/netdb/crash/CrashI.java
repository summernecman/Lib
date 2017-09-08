package com.siweisoft.service.netdb.crash;

//by summer on 17-09-08.

import com.android.lib.base.interf.OnFinishListener;

public interface CrashI {

    public void sendCrash(CrashBean crashBean, OnFinishListener onFinishListener);
}
