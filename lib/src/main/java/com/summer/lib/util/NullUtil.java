package com.summer.lib.util;

/**
 * Created by ${viwmox} on 2016-06-01.
 */
public class NullUtil {


    public static boolean isStrEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNull(Object... o) {
        for (int i = 0; i < o.length; i++) {
            if (o[i] == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNull(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }
}
