package com.summer.test.main;

//by summer on 2017-07-10.

import com.android.lib.aplication.LibAplication;
import com.avos.avoscloud.AVOSCloud;

public class TestApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "hGX5m8rfuBjCjiMVnymps39P-gzGzoHsz", "S5bOpoMHOK2tXK5epj48xaUh");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
    }
}
