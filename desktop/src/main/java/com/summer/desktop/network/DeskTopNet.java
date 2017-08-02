package com.summer.desktop.network;

//by summer on 2017-07-31.

import android.content.Context;

import com.android.lib.network.NetWork;
import com.android.lib.network.interf.OnNetWorkReqInterf;
import com.summer.desktop.module.base.bean.DesktopReqBean;

public class DeskTopNet {

    private static DeskTopNet instance;

    public static DeskTopNet getInstance() {
        if (instance == null) {
            instance = new DeskTopNet();
        }
        return instance;
    }


    public void startRequst(final Context context, final String model, Object o, final OnNetWorkReqInterf reqInterf) {
        DesktopReqBean req = new DesktopReqBean();
        req.setData(o);
        NetWork.getInstance(context).doHttpRequsetWithSession(context, model, req, reqInterf);
    }

}
