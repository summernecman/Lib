package com.android.lib.network.bean.req;

import java.io.Serializable;

/**
 * 请求实体基类
 * Created by ${viwmox} on 2016-04-27.
 */
public class BaseReqBean implements Serializable {

    public static final String _ID = "_id";
    private String _id;

    public BaseReqBean() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
