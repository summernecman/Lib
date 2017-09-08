package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragRenameBinding;
import com.siweisoft.service.netdb.user.UserBean;

public class RenameUIOpe extends BaseUIOpe<FragRenameBinding> {


    public RenameUIOpe(Context context) {
        super(context);
    }

    public void initInfo(UserBean userBean) {
        bind.setUsername(userBean);
    }
}
