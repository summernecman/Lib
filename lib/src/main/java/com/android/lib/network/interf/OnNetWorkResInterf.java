package com.android.lib.network.interf;


import com.android.lib.network.bean.res.BaseResBean;

/**
 * Created by ${viwmox} on 2016-05-16.
 */
public interface OnNetWorkResInterf<T> {
    /**
     * 网络无连接
     */
    int ERROR_TYPE_INPUT_NOT_ALL = 3;
    /**
     * 网络无连接
     */
    int ERROR_TYPE_NETCONNETCT = 0;
    /**
     * 返回数据为空
     */
    int ERROR_TYPE_DATANULL = 1;
    /**
     * success=false
     */
    int ERROR_TYPE_NOTSUCCESS = 2;

    /**
     * 正在发起网络请求
     */
    boolean onNetWorkReqStart();

    /**
     * 网络请求获取数据成功
     */
    void onNetWorkFail(BaseResBean resBean);

    void onNetWorkResSuccess(T resBean);
}
