package com.summer.desktop;

//by summer on 2017-07-28.

import com.android.lib.aplication.LibAplication;
import com.android.lib.constant.UrlConstant;
import com.android.lib.network.NetOpe;
import com.bumptech.glide.Glide;

public class DesktopApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        NetOpe.NET_DOMAIN = "http://192.168.20.177:8079";
        NetOpe.NET_DOMAIN = "http://106.14.161.168:8079";
        NetOpe.NET_URL = NetOpe.NET_DOMAIN + "/test";
        UrlConstant.URI = NetOpe.NET_DOMAIN + "/test";

        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplicationContext()).clearDiskCache();
            }
        }).start();
    }
}
