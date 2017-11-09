package com.android.lib.service.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.lib.appthread.AppThread;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;


/**
 * Created by ${viwmox} on 2016-07-12.
 */
public class AppService extends Service {


    int index = -1;

    private OnFinishListener onFinishListener;
//
//    public AppService(OnFinishListener onFinishListener) {
//        this.onFinishListener = onFinishListener;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppThread.getInstance(new OnFinishWithObjI() {
            @Override
            public void onNetFinish(Object o) {
                int i = (int) o;
                //isThisParentHaveChild(NoteBookID.BASE_PARENT_ID, name);
                LogUtil.E(getPackageName());
                Intent intent1 = new Intent(getPackageName() + ValueConstant.ACITON_GLOB_CAST);
                intent1.putExtra(ValueConstant.DATA_DATA, i);
                sendBroadcast(intent1);
            }
        }).init().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (onFinishListener != null) {
            onFinishListener.onFinish(null);
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
