package com.siweisoft.service;

//by summer on 2017-07-03.

import android.content.Intent;

import com.android.lib.aplication.LibAplication;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.UrlConstant;
import com.android.lib.exception.exception.CrashHander;
import com.android.lib.service.main.AppService;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.netdb.crash.CrashBean;
import com.siweisoft.service.netdb.crash.CrashOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.EMChatOpe;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.sms.SMSSDK;


public class ServieApp extends LibAplication implements OnFinishListener {

    CrashOpe crashI;

    @Override
    public void onCreate() {
        super.onCreate();
        UrlConstant.HTTP = "http://";
        UrlConstant.NETSTART = "sx.siweisoft.cn:8079";
        //UrlConstant.NETSTART = "192.168.20.187:8079";

        UrlConstant.URI = UrlConstant.HTTP + UrlConstant.NETSTART + "/sx";
        UrlConstant.fileUrl = UrlConstant.HTTP + UrlConstant.NETSTART + "/files";


        CrashHander.getInstance().init(this, this);


        SPUtil.getInstance().init(this);

        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

//        JCVideoPlayer.ACTION_BAR_EXIST = false;
//        JCVideoPlayer.TOOL_BAR_EXIST = false;


        startService(new Intent(this, AppService.class));
        startService(new Intent(getBaseContext(), com.siweisoft.service.AppServer.class));

        new EMChatOpe(this).initEM(this);

        //startService(new Intent(this, RecordService.class));


        SMSSDK.getInstance().initSdk(this);
        //SMSSDK.getInstance().setIntervalTime(5000);
        SMSSDK.getInstance().setDebugMode(false);

        JPushInterface.init(this);
        JPushInterface.setDebugMode(false);
        LogUtil.CAN_LOGIN = true;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void exit() {
        LogUtil.E("exit");
        super.exit();
    }


    @Override
    public void onFinish(Object o) {
        SPUtil.getInstance().saveBoolean(Value.autologin, false);
        if (crashI == null) {
            crashI = new CrashOpe(this);
        }
        if (Value.getRoom() != null) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
            EMClient.getInstance().logout(true);
        }

        final CrashBean crashBean = new CrashBean();
        crashBean.setError((String) o);
        crashBean.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        crashBean.setUserBean(Value.getUserInfo());
        crashI.sendCrash(crashBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                exit();
            }
        });
    }
}
