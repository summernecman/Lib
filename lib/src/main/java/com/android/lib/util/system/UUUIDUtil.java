package com.android.lib.util.system;

//by summer on 17-08-30.

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class UUUIDUtil {

    private static UUUIDUtil instance;

    private UUUIDUtil() {

    }

    public static UUUIDUtil getInstance() {
        if (instance == null) {
            instance = new UUUIDUtil();
        }
        return instance;
    }

    public String getUUUId(Context context) {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        return id;
    }
}
