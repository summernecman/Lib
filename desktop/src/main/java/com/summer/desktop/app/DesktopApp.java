package com.summer.desktop.app;

import com.summer.lib.aplication.LibAplication;

import cn.bmob.v3.Bmob;

/**
 * Created by summer on 2017/5/25 23:47.
 */

public class DesktopApp extends LibAplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "a372099e1546f084af11ba4cfc1b8439");
    }
}
