package com.summer.lib.util.system;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

/**
 * Created by ${viwmox} on 2017-02-16.
 */

public class PermissionUtil {

    public static PermissionUtil instance;

    public static PermissionUtil getInstance() {
        if (instance == null) {
            instance = new PermissionUtil();
        }
        return instance;
    }

    public void addPermission(Fragment f, String permission) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(PackageManager.PERMISSION_GRANTED == f.getActivity().getPackageManager().checkPermission(permission, "packageName"))) {
                f.requestPermissions(new String[]{permission}, 1);
            }
        }
    }

}
