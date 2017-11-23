package com.summer.time.ui;

import android.content.Intent;

import com.android.lib.aplication.LibAplication;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.exception.exception.CrashHander;
import com.android.lib.service.main.AppService;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;

public class TimeApp extends LibAplication implements OnFinishListener {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHander.getInstance().init(this, this);
        SPUtil.getInstance().init(this);
        startService(new Intent(this, AppService.class));
        LogUtil.CAN_LOGIN = true;

    }

    @Override
    public void onFinish(Object o) {

    }
}