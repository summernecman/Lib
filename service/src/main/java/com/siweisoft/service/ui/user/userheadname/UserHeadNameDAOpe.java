package com.siweisoft.service.ui.user.userheadname;

//by summer on 17-08-30.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;

public class UserHeadNameDAOpe extends BaseDAOpe {

    UserI userI;

    public UserHeadNameDAOpe(Context context) {
        super(context);
        userI = new UserNetOpe(context);
    }

    public void setHead(UserBean userBean, OnFinishListener onFinishListener) {
        userBean.setPhone(Value.getUserInfo().getPhone());
        userI.setHeadUrl(userBean, onFinishListener);
    }
}
