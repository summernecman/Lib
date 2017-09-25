package com.siweisoft.service.ui.Constant;

//by summer on 2017-07-04.

import android.content.Context;
import android.os.Environment;

import com.android.lib.constant.UrlConstant;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.SPUtil;
import com.hyphenate.chat.EMChatRoom;
import com.siweisoft.service.R;
import com.siweisoft.service.netdb.user.UserBean;

import java.io.File;
import java.util.ArrayList;

public class Value extends ValueConstant {


    public static int FULLSCREEN = R.id.rlroot;

    public static int ROOTID = R.id.serviceroot;

    public static int ROOTID_ONE = R.id.one;

    public static int ROOTID_TWO = R.id.two;

    public static int ROOTID_THREE = R.id.three;

    public static EMChatRoom room;

    public static int position = 0;

    public static String CACHE_FILE = "videocache";

    public static ArrayList<Integer> root = new ArrayList<>();
    public static UserBean userBean = new UserBean();

    static {
        root.add(ROOTID_ONE);
        root.add(ROOTID_TWO);
        root.add(ROOTID_THREE);
    }

    public static Integer getNowRoot() {
        return root.get(position);
    }

    public static void setPostion(int postion) {
        Value.position = postion;
    }

    public static File getCacheFile() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + CACHE_FILE);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void initNetUrl(Context context, String url) {
        UrlConstant.NETSTART = url;
        UrlConstant.URI = UrlConstant.HTTP + UrlConstant.NETSTART + ":8079/server";
        UrlConstant.fileUrl = UrlConstant.HTTP + UrlConstant.NETSTART + ":8079/files";

        SPUtil.getInstance().init(context).saveStr("url-1", url);
    }

}
