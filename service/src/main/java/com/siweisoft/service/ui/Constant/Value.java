package com.siweisoft.service.ui.Constant;

//by summer on 2017-07-04.

import android.content.Context;
import android.os.Environment;

import com.android.lib.constant.UrlConstant;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.GsonUtil;
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

    public static String ROOM;

    public static void saveRoom(EMChatRoom o) {
        SPUtil.getInstance().saveStr(ROOM, GsonUtil.getInstance().toJson(o));
        room = o;
    }

    public static EMChatRoom getRoom() {
        if (room == null) {
            room = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(ROOM), EMChatRoom.class);
        }
        return room;
    }

    public static int position = 0;

    public static String CACHE_FILE = "videocache";

    public static ArrayList<Integer> root = new ArrayList<>();

    private static UserBean userBean;

    public static final String USERBEAN = "userbean";


    public static void saveUserInfo(UserBean o) {
        SPUtil.getInstance().saveStr(USERBEAN, GsonUtil.getInstance().toJson(o));
        userBean = o;
    }

    public static UserBean getUserInfo() {
        if (userBean == null) {
            userBean = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(USERBEAN), UserBean.class);
        }
        return userBean;
    }

    static {
        root.add(ROOTID_ONE);
        root.add(ROOTID_TWO);
        root.add(ROOTID_THREE);
    }

    public static Integer getNowRoot() {
        if (root.size() < 3) {
            root.clear();
            root.add(ROOTID_ONE);
            root.add(ROOTID_TWO);
            root.add(ROOTID_THREE);
        }
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
