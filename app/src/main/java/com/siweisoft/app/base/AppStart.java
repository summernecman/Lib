package com.siweisoft.app.base;

//by summer on 2017-03-31.

import com.android.lib.aplication.LibAplication;
import com.siweisoft.app.value.Values;

public class AppStart extends LibAplication{

    @Override
    public void onCreate() {
        super.onCreate();
        new Values();
    }
}
