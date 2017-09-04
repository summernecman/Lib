package com.siweisoft.service.netdb.collection;

//by summer on 17-09-04.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.login.UserBean;

public interface CollectionI {

    public void getCollectionVideosByUserId(UserBean userBean, OnFinishListener onFinishListener);

    public void collect(VideoBean videoBean, OnFinishListener onFinishListener);
}
