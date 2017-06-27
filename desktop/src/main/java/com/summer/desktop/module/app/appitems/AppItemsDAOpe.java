package com.summer.desktop.module.app.appitems;

//by summer on 2017-06-07.

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.summer.desktop.R;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.db.ope.AppsDBOpe;

import java.util.ArrayList;

public class AppItemsDAOpe extends BaseDAOpe {

    AppListDAOpe appListDAOpe;

    AppsDBOpe appsDBOpe;

    ArrayList<AppDBBean> list = new ArrayList<>();

    public AppItemsDAOpe(Context context) {
        super(context);
        appListDAOpe = new AppListDAOpe(context);
        appsDBOpe = new AppsDBOpe(context, new AppDBBean());
    }

    public void getApps(final OnFinishListener listener) {
        list = appsDBOpe.getApps();
        if (list.size() == 0) {
            appListDAOpe.getApps("全部", new OnFinishWithObjI<ArrayList<AppDBBean>>() {
                @Override
                public void onNetFinish(ArrayList<AppDBBean> o) {
                    AppDBBean appDBBean = new AppDBBean();
                    appDBBean.setAppName("刷新");
                    appDBBean.setIcon(new BitmapDrawable(BitmapFactory.decodeResource(context.getResources(), R.drawable.app)));
                    o.add(appDBBean);
                    for (int i = 0; i < list.size(); i++) {
                        LogUtil.E(list.get(i).getAppName() + "----" + list.get(i).getPosition());
                        list.get(i).setPosition(i);
                    }
                    appsDBOpe.save(o);
                    list.addAll(o);
                    listener.onFinish(o);
                }
            });
        } else {
            final PackageManager pm = context.getPackageManager();
            for (int i = 0; i < list.size(); i++) {
                try {
                    if (list.get(i).getPosition() == null) {
                        list.get(i).setPosition(i);
                    }
                    list.get(i).setIcon(pm.getApplicationIcon(list.get(i).getPackageName()));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            listener.onFinish(list);
        }
    }

    public void clearData() {
        appsDBOpe.clear();
    }

    public void saveSort() {

        boolean should = false;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getPosition().equals(i)) {
                should = true;
            }
            LogUtil.E(list.get(i).getAppName() + "----" + list.get(i).getPosition());
            list.get(i).setPosition(i);
        }
        if (!should) {
            return;
        }
        appsDBOpe.save(list);
    }

}
