package com.summer.lib.base.ope;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.summer.lib.network.NetWork;
import com.summer.lib.network.bean.req.BaseReqBean;
import com.summer.lib.network.interf.OnNetWorkReqInterf;

/**
 * 逻辑处理类
 * Created by ${viwmox} on 2016-04-27.
 */
public class BaseNetOpe {

    protected Gson gson = new Gson();

    protected Context context;

    protected Handler handler = new Handler();

    public BaseNetOpe(Context context) {
        this.context = context;
    }

    protected void onNetLoadData(Context context, String model, BaseReqBean baseReqBean, OnNetWorkReqInterf reqInterf) {
        NetWork.getInstance(context).doHttpRequsetWithSession(context, model, baseReqBean, reqInterf);
    }
}
