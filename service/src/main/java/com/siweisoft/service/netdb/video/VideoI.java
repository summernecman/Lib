package com.siweisoft.service.netdb.video;

//by summer on 17-08-24.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.ui.user.login.UserBean;

public interface VideoI {

    public void getVideos(UserBean userBean, OnFinishListener onFinishListener);

    public void addVideo(VideoBean videoBean, OnFinishListener onFinishListener);

    public void getHistoryVideos(UserBean userBean, OnFinishListener onFinishListener);
}
