package com.summer.lib.util;

import android.util.Log;

/**
 * Created by ${viwmox} on 2016-04-27.
 */
public class LogUtil {

    private static LogUtil instance = new LogUtil();

    public static boolean CAN_LOGIN = true;

    public static void E(Object o) {
        if (!CAN_LOGIN) {
            return;
        }
        Log.e("LogUtil", o + "");
    }

    public static void E(Object tag, Object o) {
        if (!CAN_LOGIN) {
            return;
        }
        Log.e("LogUtil:" + tag, "LogUtil:" + o);
    }

    public static void init() {
        CAN_LOGIN = true;
    }

}
