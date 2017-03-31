package com.summer.lib.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by ${viwmox} on 2016-05-10.
 */
public class ToastUtil {

    private static ToastUtil instance;

    public static ToastUtil getInstance() {
        if (instance == null) {
            instance = new ToastUtil();
        }
        return instance;
    }

    public void showSueess(Context context) {
        Toast.makeText(context, "操作成功", Toast.LENGTH_LONG).show();
    }

    public void show(Context context, String msg) {
        Toast.makeText(context, StringUtil.getStr(msg), Toast.LENGTH_SHORT).show();
    }

    public void showShort(Context context, String msg) {
        Toast.makeText(context, StringUtil.getStr(msg), Toast.LENGTH_SHORT).show();
    }

    public void showLong(Context context, String msg) {
        Toast.makeText(context, StringUtil.getStr(msg), Toast.LENGTH_LONG).show();
    }


    public void showSueess(final Context context, Handler handler) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "操作成功", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void show(final Context context, final String msg, Handler handler) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, StringUtil.getStr(msg), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showShort(final Context context, final String msg, Handler handler) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, StringUtil.getStr(msg), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showLong(final Context context, final String msg, Handler handler) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, StringUtil.getStr(msg), Toast.LENGTH_LONG).show();
            }
        });

    }
}
