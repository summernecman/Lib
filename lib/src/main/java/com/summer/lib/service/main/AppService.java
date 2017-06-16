package com.summer.lib.service.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.summer.lib.appthread.AppThread;
import com.summer.lib.base.interf.OnFinishWithObjI;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.LogUtil;


/**
 * Created by ${viwmox} on 2016-07-12.
 */
public class AppService extends Service {


    int index = -1;

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
}
