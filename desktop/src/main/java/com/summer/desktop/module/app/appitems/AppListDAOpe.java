package com.summer.desktop.module.app.appitems;

//by summer on 2017-04-01.

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.PackageUtil;
import com.summer.desktop.bean.dbbean.AppDBBean;

import java.util.ArrayList;
import java.util.List;

public class AppListDAOpe extends BaseDAOpe {


    public AppListDAOpe(Context context) {
        super(context);
    }

    public void getApps(final String type, final OnFinishWithObjI<ArrayList<AppDBBean>> ObjInter) {
        final PackageManager pm = context.getPackageManager();
        PackageUtil.getInstance().getPackageInfoList(context, type, new OnFinishWithObjI() {
            @Override
            public void onNetFinish(final Object o) {

                new AsyncTask<String, String, ArrayList<AppDBBean>>() {
                    @Override
                    protected ArrayList<AppDBBean> doInBackground(String... params) {
                        List<ApplicationInfo> applicationInfos = (List<ApplicationInfo>) o;
                        ArrayList<AppDBBean> appDBBeen = new ArrayList<AppDBBean>();
                        for (int i = 0; i < applicationInfos.size(); i++) {

                            AppDBBean a = new AppDBBean();
                            a.setGroupName(type);
                            a.setAppName("" + applicationInfos.get(i).loadLabel(context.getPackageManager()));
                            a.setPackageName(applicationInfos.get(i).packageName);
                            appDBBeen.add(a);
                            try {
                                a.setIcon(pm.getApplicationIcon(a.getPackageName()));
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        return appDBBeen;
                    }

                    @Override
                    protected void onPostExecute(ArrayList<AppDBBean> appDBBeen) {
                        if (ObjInter != null) {
                            ObjInter.onNetFinish(appDBBeen);
                        }
                    }
                }.execute();


            }
        });
    }
}
