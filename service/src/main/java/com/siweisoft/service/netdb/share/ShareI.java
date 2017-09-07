package com.siweisoft.service.netdb.share;

//by summer on 17-09-06.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.ui.user.login.UserBean;

public interface ShareI {

    public void share(ShareBean shareBean, OnFinishListener onFinishListener);

    public void getShareNumByUserPhone(UserBean userBean, OnFinishListener onFinishListener);

    public void getSharesByReceipt(ShareBean shareBean, OnFinishListener onFinishListener);
}
