package com.summer.desktop.module.app.appmain;

//by summer on 2017-06-07.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.module.app.appitems.AppListDAOpe;
import com.summer.desktop.module.app.folder.NormalFragment;

import java.util.ArrayList;

public class AppMainDAOpe extends BaseDAOpe {


    AppListDAOpe appListDAOpe;


    public AppMainDAOpe(Context context) {
        super(context);
        appListDAOpe = new AppListDAOpe(context);
    }

    public void getApps(final OnFinishListener listener) {
        appListDAOpe.getApps("全部", new OnFinishWithObjI<ArrayList<AppDBBean>>() {
            @Override
            public void onNetFinish(ArrayList<AppDBBean> o) {
                listener.onFinish(o);
            }
        });
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NormalFragment());
        return fragments;
    }

    public void setApps(ArrayList<AppDBBean> apps) {

    }
}
