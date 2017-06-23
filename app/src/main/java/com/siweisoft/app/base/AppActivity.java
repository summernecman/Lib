package com.siweisoft.app.base;

//by summer on 2017-03-31.

import android.os.Bundle;
import android.os.PersistableBundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIBean;

public abstract class AppActivity<A extends BaseUIBean, B extends BaseDAOpe> extends BaseUIActivity<A, B> {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
