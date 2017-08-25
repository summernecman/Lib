package com.siweisoft.service;

//by summer on 2017-07-03.

import com.android.lib.aplication.LibAplication;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.SPUtil;
import com.siweisoft.service.ui.Constant.VideoValue;

import org.xutils.x;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ServieApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        UrlConstant.URI = "http://106.14.161.168:8079/server";
        UrlConstant.URI = "http://192.168.20.175:8079/server";


        VideoValue.URL.IP = "106.14.161.168";
        VideoValue.URL.PROT = 8906;
        SPUtil.getInstance().init(this);

        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

        JCVideoPlayer.ACTION_BAR_EXIST = false;
        JCVideoPlayer.TOOL_BAR_EXIST = false;
    }

    @Override
    public void exit() {
        super.exit();
    }
}
