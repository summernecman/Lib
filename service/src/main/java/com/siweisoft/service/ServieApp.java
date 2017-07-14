package com.siweisoft.service;

//by summer on 2017-07-03.

import com.android.lib.aplication.LibAplication;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.SPUtil;
import com.avos.avoscloud.AVOSCloud;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import org.xutils.x;

public class ServieApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "hGX5m8rfuBjCjiMVnymps39P-gzGzoHsz", "S5bOpoMHOK2tXK5epj48xaUh");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
        UrlConstant.URI = "http://192.168.20.184:8079";
        SPUtil.getInstance().init(this);

        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }

    @Override
    public void exit() {
        super.exit();
        ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
    }
}
