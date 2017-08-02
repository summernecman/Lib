package com.android.lib.network.netadapter;

//by summer on 2017-08-01.

import com.android.lib.network.NetOpe;
import com.android.lib.network.bean.res.BaseResBean;

public class OnNetProcessAdapter<T extends BaseResBean> implements NetOpe.onNetProcess<T> {


    @Override
    public void onStart(String url, String req) {

    }

    @Override
    public void onEnd(int type, String res) {

    }

    @Override
    public void onResult(T t) {

    }
}
