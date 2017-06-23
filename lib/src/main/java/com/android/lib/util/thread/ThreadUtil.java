package com.android.lib.util.thread;

import android.os.AsyncTask;
import android.os.Handler;

import com.android.lib.base.interf.OnLoadingInterf;

/**
 * Created by ${viwmox} on 2017-02-20.
 */

public class ThreadUtil {

    Integer max = 0;
    Handler handler = new Handler();
    private boolean stop = false;

    public void run(final long time, final OnLoadingInterf listener) {
        new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                while (!stop) {
                    max++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStarLoading(max);
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listener.onStopLoading(max);
            }
        }.execute();
    }

    public void stop() {
        stop = true;
    }


}
