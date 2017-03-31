package com.siweisoft.app.base;

//by summer on 2017-03-31.

import com.siweisoft.app.value.Values;
import com.summer.lib.aplication.LibAplication;

public class AppStart extends LibAplication{

    @Override
    public void onCreate() {
        super.onCreate();
        new Values();
    }
}
