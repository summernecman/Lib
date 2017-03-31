package com.summer.lib.appthread;


import com.summer.lib.base.interf.OnNetFinishWithObjInter;

/**
 * Created by ${viwmox} on 2016-07-22.
 */
public class AppThread extends Thread {

    private static AppThread instance;

    public boolean stop = true;

    public boolean pause = true;

    public long sleepTime = 6000;

    private static OnNetFinishWithObjInter o;

    int count = 0;

    private AppThread() {

    }

    public static AppThread getInstance(OnNetFinishWithObjInter o) {
        AppThread.o = o;
        if (instance == null) {
            instance = new AppThread();
        }
        return instance;
    }


    public AppThread init() {
        stop = false;
        pause = false;
        count = 0;
        return instance;
    }

    @Override
    public void run() {
        super.run();
        while (!stop) {
            if (!pause) {
                doThing(count);
                try {
                    Thread.sleep(sleepTime);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doThing(int count) {
        if (o != null) {
            o.onNetFinish(count);
        }
    }
}
