package com.android.lib.network.netadapter;

import android.content.Context;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.db.NetCacheDBBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;
import com.android.lib.network.ope.NetCacheDBOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NetWorkUtil;
import com.google.gson.Gson;

/**
 * Created by ${viwmox} on 2016-05-15.
 */
public abstract class OnNetWorkReqCacheAdapter implements OnNetWorkReqInterf {

    protected Context context;
    protected String tag;
    Gson gson = new Gson();

    public OnNetWorkReqCacheAdapter(Context context) {
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
        LogUtil.E(gson.toJson(baseResBean));
        if (!haveData) {
            onNetWorkResult(false, baseResBean);
            switch (baseResBean.getErrorCode()) {
                case ValueConstant.ERROR_CODE_NET_NO_CONNETCT:
                    NetCacheDBOpe netCacheDBOpe = new NetCacheDBOpe(context, new NetCacheDBBean());
                    //netCacheDBOpe.save(MethodValue.getUserInfo(context).getData().getUser().getDisplayname(),baseResBean.getData().toString(),url);
                    break;
            }
        } else {
//            if (baseResBean.isException()) {
//                onNetWorkResult(false, baseResBean);
//            } else {
//                if (baseResBean.getData() == null) {
////                    baseResBean.setErrorMessage(ValueConstant.ERROR_STR_DATA_NULL);
////                    baseResBean.setErrorCode(ValueConstant.ERROR_CODE_DATA_NULL);
////                    onNetWorkResult(false,baseResBean);
//
//                    onNetWorkResult(true, baseResBean);
//                } else {
//                    onNetWorkResult(true, baseResBean);
//                }
//            }

        }
    }

    public abstract void onNetWorkResult(boolean success, BaseResBean o);

}
