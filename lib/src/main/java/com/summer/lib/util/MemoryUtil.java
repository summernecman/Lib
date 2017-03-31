package com.summer.lib.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by ${viwmox} on 2016-08-30.
 */
public class MemoryUtil {

    private static MemoryUtil instance;

    public static MemoryUtil getInstance() {
        if (instance == null) {
            instance = new MemoryUtil();
        }
        return instance;
    }

    public void clear(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        getAvailMemory(context);
        List<ActivityManager.RunningAppProcessInfo> infoList = am.getRunningAppProcesses();
        for (int i = 0; i < infoList.size(); i++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = infoList.get(i);
            if (true) {
                String[] pkgList = runningAppProcessInfo.pkgList;
                for (int j = 0; j < pkgList.length; j++) {
                    am.killBackgroundProcesses(pkgList[j]);
                    LogUtil.E(pkgList[j]);
                }
            }
        }
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(100);
        getAvailMemory(context);
    }

    public long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
        LogUtil.E(mi.availMem / (1024 * 1024));
        return mi.availMem / (1024 * 1024);
    }
}
