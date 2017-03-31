package com.summer.lib.util.system;

import android.os.Handler;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2017-02-23.
 */

public class HandleUtil extends Handler implements Serializable {

    private static HandleUtil instance;


    public static HandleUtil getInstance() {
        if (instance == null) {
            instance = new HandleUtil();
        }
        return instance;
    }


}
