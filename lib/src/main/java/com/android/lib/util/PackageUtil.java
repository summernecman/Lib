package com.android.lib.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.android.lib.base.interf.OnFinishWithObjI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ${viwmox} on 2016-06-30.
 */
public class PackageUtil {

    public static final int FLAG_SYSTEM = 0;
    public static final int FLAG_ALL = -1;
    public static final int FLAG_USER = 1;
    public static final int FLAG_FREQUENT = 2;
    public static final int FLAG_COMMON = 3;
    public static final int FLAG_NULL = 4;
    private static PackageUtil instance;

    public static PackageUtil getInstance() {
        if (instance == null) {
            instance = new PackageUtil();
        }
        return instance;
    }

    public void getPackageInfoList(final Context context, final String type, final OnFinishWithObjI onFinishWithObjI) {
        new AsyncTask<String, Integer, List<ApplicationInfo>>() {
            @Override
            protected List<ApplicationInfo> doInBackground(String... params) {
                List<ApplicationInfo> infos = context.getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
                Collections.sort(infos, new ApplicationInfo.DisplayNameComparator(context.getPackageManager()));
                List<ApplicationInfo> infoss = new ArrayList<>();
                switch (type) {
                    case "全部":
                        for (ApplicationInfo applicationInfo : infos) {
                            if (context.getPackageManager().getLaunchIntentForPackage(applicationInfo.packageName) != null) {
                                infoss.add(applicationInfo);
                            }
                        }
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
                if (onFinishWithObjI != null) {
                    onFinishWithObjI.onNetFinish(applicationInfos);
                }
            }
        }.execute();
    }

    public void getPackageApplicationInfo(final Context context, final List<String> packageNames, final OnFinishWithObjI onFinishWithObjI) {

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
                if (onFinishWithObjI != null) {
                    onFinishWithObjI.onNetFinish(applicationInfos);
                }
            }
        }.execute();

    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 返回当前程序版本名
     */
    public static int getAppVersionCode(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }
}
