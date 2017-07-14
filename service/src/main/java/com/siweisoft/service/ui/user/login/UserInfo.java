package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.databinding.ObservableField;

import com.android.lib.bean.BaseBean;

public class UserInfo extends BaseBean {

    public static final String TYPE_SERVICE = "type_service";
    public static final String TYPE_CUSTOMER = "type_customer";
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> pwd = new ObservableField<>();
    public final ObservableField<Boolean> type = new ObservableField<>();//是否hi客户
    public final ObservableField<Integer> userid = new ObservableField<>();


    public UserInfo() {
        type.set(false);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name=" + name +
                ", pwd=" + pwd +
                ", type=" + type +
                ", userid=" + userid +
                '}';
    }
}
