package com.siweisoft.app.home;

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.app.bean.dbbean.AppDBBean;
import com.siweisoft.app.impl.IApp;
import com.siweisoft.app.ope.AppListDAOpe;

import java.util.ArrayList;

public class HomeDAOpe extends BaseDAOpe implements IApp {

    AppListDAOpe appListDAOpe;

    public HomeDAOpe(Context context) {
        super(context);
        appListDAOpe = new AppListDAOpe(context);
    }

    @Override
    public void getApps(final OnFinishListener listener) {
        appListDAOpe.getApps("全部", new OnFinishWithObjI<ArrayList<AppDBBean>>() {
            @Override
            public void onNetFinish(ArrayList<AppDBBean> o) {
                listener.onFinish(o);
            }
        });
    }

    @Override
    public void setApps(ArrayList<AppDBBean> apps) {

    }

}