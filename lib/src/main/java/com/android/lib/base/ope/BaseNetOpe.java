package com.android.lib.base.ope;

import android.content.Context;

import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;

/**
 * 网络请求逻辑处理类
 * Created by ${viwmox} on 2016-04-27.
 */
public class BaseNetOpe {

    protected Context context;

    public BaseNetOpe(Context context) {
        this.context = context;
    }

    protected void onNetLoadData(Context context, String model, BaseReqBean baseReqBean, OnNetWorkReqInterf reqInterf) {
        NetWork.getInstance(context).doHttpRequsetWithSession(context, model, baseReqBean, reqInterf);
    }
}
