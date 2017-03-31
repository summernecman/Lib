package com.summer.lib.network.interf;


import com.summer.lib.network.bean.res.BaseResBean;

/**
 * Created by ${viwmox} on 2016-04-27.
 */
public interface OnNetWorkReqInterf {


    /**
     * 正在发起网络请求
     */
    boolean onNetWorkReqStart(String reqjson, String tag);

    /**
     * 网络请求完成
     */
    void onNetWorkReqFinish(boolean haveData, String url, BaseResBean baseResBean);

    /**
     * 网络请求获取数据结果
     */
    void onNetWorkResult(boolean success, Object o);


}
