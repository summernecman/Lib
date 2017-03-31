package com.summer.lib.util;

/**
 * Created by ${viwmox} on 2016-06-01.
 */
public class ObjectUtil {
    private static ObjectUtil instance;

    private ObjectUtil() {

    }

    public static ObjectUtil getInstance() {
        if (instance == null) {
            instance = new ObjectUtil();
        }
        return instance;
    }

}
