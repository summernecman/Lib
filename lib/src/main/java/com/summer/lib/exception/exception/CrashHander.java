package com.summer.lib.exception.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.summer.lib.aplication.LibAplication;
import com.summer.lib.base.activity.BaseActivity;
import com.summer.lib.util.LogUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * Created by ${viwmox} on 2016-07-20.
 */
public class CrashHander implements Thread.UncaughtExceptionHandler {

    private static CrashHander instance;

    Context context;

    Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public static CrashHander getInstance() {
        if (instance == null) {
            instance = new CrashHander();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        if (true) {
            showException();
        }
        if (true) {
            String result = print(ex);
            saveInfo(ex, result);
        }
        if (true) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            uncaughtExceptionHandler.uncaughtException(thread, ex);
            //restart(thread, ex);
        }
    }

    private void restart(final Thread thread, final Throwable ex) {
        LibAplication libAplication = null;
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            libAplication = (LibAplication) activity.getApplication();
        } else if (context instanceof LibAplication) {
            libAplication = (LibAplication) context;
        } else {
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        }
//        Intent intent = new Intent(context, IndexActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) libAplication.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC,System.currentTimeMillis()+500,pendingIntent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private String print(Throwable ex) {
        //如果用户没有处理则让系统默认的异常处理器来处理
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        LogUtil.E(result);
        return result;
    }

    public void saveInfo(Throwable ex, String result) {
        LogUtil.E(ex.getMessage() + "---" + result);
    }

    private boolean showException() {
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        return true;
    }


}
