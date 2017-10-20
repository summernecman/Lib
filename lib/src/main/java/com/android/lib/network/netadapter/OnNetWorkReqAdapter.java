package com.android.lib.network.netadapter;

import android.content.Context;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;
import com.android.lib.util.NetWorkUtil;
import com.google.gson.Gson;

/**
 * Created by ${viwmox} on 2016-05-15.
 */
public abstract class OnNetWorkReqAdapter implements OnNetWorkReqInterf {

    protected Context context;
    protected String tag;
    Gson gson = new Gson();

    public OnNetWorkReqAdapter(Context context) {
        this.context = context;
    }

    @Override
    public boolean onNetWorkReqStart(String tag, String reqjson) {
        this.tag = tag;
        boolean isNetOk = NetWorkUtil.getInstance().getNetisAvailable(context);
        return isNetOk;
    }

    @Override
    public void onNetWorkReqFinish(boolean haveData, String url, BaseResBean baseResBean) {
        if (!haveData) {
            onNetWorkResult(false, baseResBean);
        } else {
            if (baseResBean.isException()) {
                onNetWorkResult(false, baseResBean);
            } else {
                onNetWorkResult(true, baseResBean);
            }

        }
    }

    public abstract void onNetWorkResult(boolean success, BaseResBean o);

    public void onNetWorkProgress(long total, long current) {

    }

    ;

}
