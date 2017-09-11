package com.siweisoft.service.ui.user.unit;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

public class UnitDAOpe extends BaseDAOpe {

    UserI userI;

    public UnitDAOpe(Context context) {
        super(context);
    }

    public void updateUnitInfo(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.updateUnit(userBean, onFinishListener);
    }
}
