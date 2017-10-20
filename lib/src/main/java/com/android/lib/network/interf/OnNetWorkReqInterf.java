package com.android.lib.network.interf;


import com.android.lib.network.bean.res.BaseResBean;

/**
 * Created by ${viwmox} on 2016-04-27.
 */
public interface OnNetWorkReqInterf<T extends BaseResBean> {


    /**
     * 正在发起网络请求
     */
    boolean onNetWorkReqStart(String reqjson, String tag);

    /**
     * 网络请求完成
     */
    void onNetWorkReqFinish(boolean haveData, String url, T baseResBean);

    /**
     * 网络请求获取数据结果
     */
    void onNetWorkResult(boolean success, T o);

    void onNetWorkProgress(long total, long current);


}
