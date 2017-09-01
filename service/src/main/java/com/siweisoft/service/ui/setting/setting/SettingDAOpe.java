package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

public class SettingDAOpe extends BaseDAOpe {

    UserI userI;

    public SettingDAOpe(Context context) {
        super(context);
        userI = new UserNetOpe(context);
    }


}
