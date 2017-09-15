package com.siweisoft.service;

//by summer on 2017-07-03.

import android.content.Intent;

import com.android.lib.aplication.LibAplication;
import com.android.lib.constant.UrlConstant;
import com.android.lib.service.main.AppService;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.siweisoft.service.netdb.crash.CrashOpe;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.util.RecordService;

import org.xutils.x;


public class ServieApp extends LibAplication {

    CrashOpe crashOpe;

    @Override
    public void onCreate() {
        super.onCreate();
        UrlConstant.HTTP = "http://";
        UrlConstant.NETSTART = "106.14.161.168";
        UrlConstant.NETSTART = "192.168.20.175";

        UrlConstant.URI = UrlConstant.HTTP + UrlConstant.NETSTART + ":8079/server";
        UrlConstant.fileUrl = UrlConstant.HTTP + UrlConstant.NETSTART + ":8079/files";

        VideoValue.URL.IP = "106.14.161.168";
        VideoValue.URL.PROT = 8906;
        SPUtil.getInstance().init(this);


        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

//        JCVideoPlayer.ACTION_BAR_EXIST = false;
//        JCVideoPlayer.TOOL_BAR_EXIST = false;

        startService(new Intent(this, AppService.class));


        initEM();

        startService(new Intent(this, RecordService.class));
    }


    public void initEM() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
            LogUtil.E("enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAutoLogin(false);
        EMClient.getInstance().init(this, options);//初始化
        EMClient.getInstance().setDebugMode(true);//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
    }

    @Override
    public void exit() {
        super.exit();
    }
}
