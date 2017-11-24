package com.android.lib.util;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * 字符串处理工具
 */
public class StringUtil {

    private static StringUtil stringUtil;

    private StringUtil() {

    }

    /**
     * 单例模式
     */
    public static StringUtil getInstance() {
        if (stringUtil == null) {
            stringUtil = new StringUtil();
        }
        return stringUtil;
    }

    /**
     * 将可能为null的字符串处理为""
     */
    public static String getStr(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 截取从@parem start 以后的字符串
     *
     * @param str
     * @param start
     * @return
     */
    public static String getSpilitStr(String str, int start) {
        if (str == null) {
            return "";
        }
        return str.substring(start, str.length());
    }

    /**
     * 将可能为null的对象处理为""
     */
    public static String getStr(Object str) {
        if (str == null) {
            return "";
        }
        return str.toString();
    }

    /**
     * 将可能为null的Int处理为""
     */
    public static String getStr(Integer str) {
        if (str == null) {
            return "";
        }
        return str + "";
    }

    /**
     * 去掉换行
     */
    public static String getNewLineStr(String str) {
        return str.replace(" ", "\n\t");
    }

    /**
     * 将字符串数组链接成list
     */
    public static void strarrToList(String[] strings, ArrayList<String> list) {
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
    }

    /**
     * 获取系统字符串Res
     */
    public String getStringRes(Context context, int strId) {
        String str = context.getResources().getString(strId);
        str = str == null ? "" : str;
        return str;
    }

    /**
     * 获取uri对应的本地文件路径
     *
     * @param context
     * @param uri
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getPath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }

        String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else {
            if (ContentResolver.SCHEME_FILE.equals(scheme)) {
                data = uri.getPath();
            } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    String wholeID = DocumentsContract.getDocumentId(uri);
                    String id = wholeID.split(":")[1];
                    String[] column = {MediaStore.Images.Media.DATA};
                    String sel = MediaStore.Images.Media._ID + "=?";
                    Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{id}, null);
                    int columnIndex = cursor.getColumnIndex(column[0]);
                    if (cursor.moveToFirst()) {
                        data = cursor.getString(columnIndex);
                    }
                    cursor.close();
                } else {
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    data = cursor.getString(column_index);
                }

            } else {
                data = null;
            }
        }
        return data;
    }

    public static String secondToMinute(int second) {
        if (second < 60) {
            return second + "秒";
        }
        int m = second / 60;
        int s = second % 60;
        return m + "分" + s + "秒";
    }

    public static String secondToMinute(long second) {
        if (second < 60) {
            return second + "秒";
        }
        long m = second / 60;
        long s = second % 60;
        if (m < 60) {
            return m + "分" + s + "秒";
        }

        long h = m / 60;
        long mm = m % 60;
        return h + "时" + mm + "分" + s + "秒";
    }

    public static String getAddZero(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }


}
