package com.siweisoft.service;

//by summer on 2017-07-03.

import android.content.Intent;

import com.android.lib.aplication.LibAplication;
import com.android.lib.constant.UrlConstant;
import com.android.lib.service.main.AppService;
import com.android.lib.util.SPUtil;
import com.siweisoft.service.netdb.crash.CrashOpe;
import com.siweisoft.service.ui.chat.videochat.EMChatOpe;

import org.xutils.x;

import cn.jpush.sms.SMSSDK;


public class ServieApp extends LibAplication {

    CrashOpe crashOpe;

    @Override
    public void onCreate() {
        super.onCreate();
        UrlConstant.HTTP = "http://";
        UrlConstant.NETSTART = "106.14.161.168";
        //UrlConstant.NETSTART = "192.168.20.175";

        UrlConstant.URI = UrlConstant.HTTP + UrlConstant.NETSTART + ":8079/server";
        UrlConstant.fileUrl = UrlConstant.HTTP + UrlConstant.NETSTART + ":8079/files";

        SPUtil.getInstance().init(this);

        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

//        JCVideoPlayer.ACTION_BAR_EXIST = false;
//        JCVideoPlayer.TOOL_BAR_EXIST = false;

        startService(new Intent(this, AppService.class));


        new EMChatOpe(this).initEM(this);

        //startService(new Intent(this, RecordService.class));


        SMSSDK.getInstance().initSdk(this);
        //SMSSDK.getInstance().setIntervalTime(5000);
        SMSSDK.getInstance().setDebugMode(true);
    }


    @Override
    public void exit() {
        super.exit();
    }
}
