package com.siweisoft.app.main;

//by summer on 2017-04-06.

import android.content.Context;

import com.siweisoft.app.bean.uibean.MainUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

public class MainUIOpe extends BaseUIOpe<MainUIBean>{


    public MainUIOpe(Context context) {
        super(context, new MainUIBean(context,null));
    }
}
