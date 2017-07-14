package com.android.lib.base.netadapter;

import android.content.Context;
import android.os.AsyncTask;

import com.android.lib.network.bean.res.BaseResBean;

/**
 * Created by ${viwmox} on 2017-02-20.
 */

public abstract class AsynecDelayUINetAdapter extends DelayUINetAdapter {


    public AsynecDelayUINetAdapter(Context context) {
        super(context);
    }

    public AsynecDelayUINetAdapter(Context context, int delay) {
        super(context, delay);
    }

    @Override
    public void onNetWorkResult(final boolean success, final BaseResBean o) {
        new AsyncTask<String, String, Object>() {
            @Override
            protected Object doInBackground(String... params) {
                onAsyncStart(success, o);
                return o;
            }

            @Override
            protected void onPostExecute(Object s) {
                super.onPostExecute(s);
                onAsyncStop(s);
            }
        }.execute();

    }

    public abstract void onAsyncStart(boolean success, Object o);

    public abstract void onAsyncStop(Object o);
}
