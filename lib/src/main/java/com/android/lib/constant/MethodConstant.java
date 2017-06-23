package com.android.lib.constant;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.lib.util.GsonUtil;
import com.android.lib.util.SPUtil;


/**
 * 方法常量
 * Created by ${viwmox} on 2016-05-12.
 */
public class MethodConstant {
    /**
     * 获取账号
     */
    public static String getAccount(Context context) {
        return SPUtil.getInstance().init(context).getAccount();
    }

    /**
     * 转化成data格式的json字符串
     */
    public static String toObject(Object o) {
        return "{data:" + GsonUtil.getInstance().toJson(o) + "}";
    }

    /**
     * 获取版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (info != null) {
                return info.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
