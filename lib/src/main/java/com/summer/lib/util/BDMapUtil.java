package com.summer.lib.util;

import android.content.Context;

/**
 * Created by ${viwmox} on 2016-05-10.
 */
public class BDMapUtil {

    private static BDMapUtil instance;

    public static BDMapUtil getInstance() {
        if (instance == null) {
            instance = new BDMapUtil();
        }
        return instance;
    }

    public void goTo(Context context, String addr) {

        MapUtil.getInstance().goToMap(context, addr);
//        try {
//            Intent intent= Intent.getIntent("intent://map/geocoder?address=" + addr + "&src=思伟软件|swsoft#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//            activity.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ToastUtil.getInstance().show(activity,"请安装百度地图");
//        }
    }
}
