package com.android.lib.network;

//by summer on 2017-07-31.

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;

public class onNetWrokResAdapter<T extends BaseResBean> implements OnNetWorkReqInterf<T> {


    @Override
    public boolean onNetWorkReqStart(String reqjson, String tag) {
        return false;
    }

    @Override
    public void onNetWorkReqFinish(boolean haveData, String url, T baseResBean) {

    }

    @Override
    public void onNetWorkResult(boolean success, T o) {

    }

    @Override
    public void onNetWorkProgress(long total, long current) {

    }
}
