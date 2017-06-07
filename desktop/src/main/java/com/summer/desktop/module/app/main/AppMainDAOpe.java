package com.summer.desktop.module.app.main;

//by summer on 2017-06-07.

import android.content.Context;

import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.interf.OnNetFinishWithObjInter;
import com.summer.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class AppMainDAOpe extends BaseDAOpe {
    AppListDAOpe appListDAOpe;


    public AppMainDAOpe(Context context) {
        super(context);
        appListDAOpe = new AppListDAOpe(context);
    }

    public void getApps(final OnFinishListener listener) {
        appListDAOpe.getApps("全部", new OnNetFinishWithObjInter<ArrayList<AppDBBean>>() {
            @Override
            public void onNetFinish(ArrayList<AppDBBean> o) {
                listener.onFinish(o);
            }
        });
    }

    public void setApps(ArrayList<AppDBBean> apps) {

    }
}
