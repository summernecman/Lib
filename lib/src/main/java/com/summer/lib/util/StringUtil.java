package com.summer.lib.util;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by summer on 2016/3/15 0015 13:58.
 */
public class StringUtil {

    private static StringUtil stringUtil;

    private StringUtil() {

    }

    public static StringUtil getInstance() {
        if (stringUtil == null) {
            stringUtil = new StringUtil();
        }
        return stringUtil;
    }

    public String getStringRes(Context context, int strId) {
        String str = context.getResources().getString(strId);
        str = str == null ? "" : str;
        return str;
    }

    public static String getStr(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getSpilitStr(String str, int start) {
        if (str == null) {
            return "";
        }
        return str.substring(start, str.length());
    }

    public static String getStr(Object str) {
        if (str == null) {
            return "";
        }
        return str.toString();
    }

    public static String getStr(Integer str) {
        if (str == null) {
            return "";
        }
        return str + "";
    }

    public static String getNewLineStr(String str) {
        return str.replace(" ", "\n\t");
    }

    public static void strarrToList(String[] strings, ArrayList<String> list) {
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
    }
}
