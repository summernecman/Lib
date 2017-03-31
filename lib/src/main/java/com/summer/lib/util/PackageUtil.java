package com.summer.lib.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;


import com.summer.lib.base.interf.OnNetFinishWithObjInter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ${viwmox} on 2016-06-30.
 */
public class PackageUtil {

    private static PackageUtil instance;

    public static final int FLAG_SYSTEM = 0;

    public static final int FLAG_ALL = -1;

    public static final int FLAG_USER = 1;

    public static final int FLAG_FREQUENT = 2;

    public static final int FLAG_COMMON = 3;

    public static final int FLAG_NULL = 4;

    public static PackageUtil getInstance() {
        if (instance == null) {
            instance = new PackageUtil();
        }
        return instance;
    }

    public void getPackageInfoList(final Context context, final String type, final OnNetFinishWithObjInter onNetFinishWithObjInter) {
        new AsyncTask<String, Integer, List<ApplicationInfo>>() {
            @Override
            protected List<ApplicationInfo> doInBackground(String... params) {
                List<ApplicationInfo> infos = context.getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
                Collections.sort(infos, new ApplicationInfo.DisplayNameComparator(context.getPackageManager()));
                List<ApplicationInfo> infoss = new ArrayList<>();
                switch (type) {
                    case "全部":
                        infoss.addAll(infos);
                        break;
                    case "系统":
                        for (ApplicationInfo applicationInfo : infos) {
                            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                                infoss.add(applicationInfo);
                            }
                        }
                        break;
                    case "用户":
                        for (ApplicationInfo applicationInfo : infos) {
                            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0 || (applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                                infoss.add(applicationInfo);
                            }
                        }
                        break;
                }
                return infoss;
            }

            @Override
            protected void onPostExecute(List<ApplicationInfo> applicationInfos) {
                if (onNetFinishWithObjInter != null) {
                    onNetFinishWithObjInter.onNetFinish(applicationInfos);
                }
            }
        }.execute();
    }

    public void getPackageApplicationInfo(final Context context, final List<String> packageNames, final OnNetFinishWithObjInter onNetFinishWithObjInter) {

        final List<ApplicationInfo> applicationInfos = new ArrayList<>();
        new AsyncTask<String, String, List<ApplicationInfo>>() {
            @Override
            protected List<ApplicationInfo> doInBackground(String... params) {
                try {
                    for (int i = 0; i < packageNames.size(); i++) {
                        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageNames.get(i), 0);
                        if (applicationInfo != null) {
                            applicationInfos.add(applicationInfo);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                return applicationInfos;
            }

            @Override
            protected void onPostExecute(List<ApplicationInfo> applicationInfos) {
                if (onNetFinishWithObjInter != null) {
                    onNetFinishWithObjInter.onNetFinish(applicationInfos);
                }
            }
        }.execute();

    }
}
