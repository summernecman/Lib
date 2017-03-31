package com.summer.lib.util;

/**
 * Created by ${viwmox} on 2016-04-27.
 */
public class ShadowUtil {

    private static ShadowUtil instance;

    private ShadowUtil() {

    }

    public static ShadowUtil getInstance() {
        if (instance == null) {
            instance = new ShadowUtil();
        }
        return instance;
    }
}
