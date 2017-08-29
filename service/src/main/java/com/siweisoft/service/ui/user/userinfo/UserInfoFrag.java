package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class UserInfoFrag extends BaseUIFrag<UserInfoUIOpe, UserInfoDAOpe> {

    @Override
    public void doThing() {
        getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initTips(getP().getD().getData());
        getP().getD().getRemarks(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initRemarks((ArrayList<CommentBean>) o);
            }
        });
    }
}
