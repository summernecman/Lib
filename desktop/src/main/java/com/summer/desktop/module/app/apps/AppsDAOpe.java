package com.summer.desktop.module.app.apps;

//by summer on 2017-06-07.

import android.content.Context;

import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.module.app.main.AppListDAOpe;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.interf.OnNetFinishWithObjInter;
import com.summer.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class AppsDAOpe extends BaseDAOpe {

    AppListDAOpe appListDAOpe;

    public AppsDAOpe(Context context) {
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

}
