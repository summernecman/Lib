package com.summer.lib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by ${viwmox} on 2016-05-26.
 */
public class MapUtil {

    private static MapUtil instance;

    private MapUtil() {

    }

    public static MapUtil getInstance() {
        if (instance == null) {
            instance = new MapUtil();
        }
        return instance;
    }

    public void goToMap(Context context, String addr) {
        Uri mUri = Uri.parse("geo:0,0?q=" + addr);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
        try {
            context.startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.getInstance().show(context, "请安装地图类软件哦");
        }
    }
}
