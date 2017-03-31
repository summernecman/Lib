package com.summer.lib.util;

import com.google.gson.Gson;

/**
 * Created by ${viwmox} on 2016-06-23.
 */
public class GsonUtil {

    private static Gson instance;

    public static Gson getInstance() {
        if (instance == null) {
            instance = new Gson();
        }
        return instance;
    }
}
