package com.summer.desktop.app;

import android.content.Intent;

import com.android.lib.aplication.LibAplication;
import com.android.lib.service.main.AppService;

import cn.bmob.v3.Bmob;

/**
 * Created by summer on 2017/5/25 23:47.
 */

public class DesktopApp extends LibAplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "a372099e1546f084af11ba4cfc1b8439");
        startService(new Intent(this, AppService.class));
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Glide.get(getApplicationContext()).clearDiskCache();
            }
        }).start();
    }
}
