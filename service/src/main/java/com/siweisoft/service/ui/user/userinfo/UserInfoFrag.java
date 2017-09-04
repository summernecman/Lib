package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;
import java.util.HashMap;

public class UserInfoFrag extends BaseServerFrag<UserInfoUIOpe, UserInfoDAOpe> {

    @Override
    public void doThing() {
        setTitleBean(new TitleBean("返回", "信息", ""));
        getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().
                getUserCenterDAOpe().
                getUserTips(getP().getD().getUserBean(),
                        new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                getP().getU().initTips((HashMap<Integer, TipBean>) o);
                            }
                        });
        getP().getD().getRemarks(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initRemarks((ArrayList<CommentBean>) o);
            }
        });

        getP().getD().getUserCallInfo(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initCallInfo((VideoTimeBean) o);
            }
        });
    }
}
