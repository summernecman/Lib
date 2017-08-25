package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class VideoPlayDAOpe extends BaseDAOpe {

    VideoBean videoBean;

    UserInfoDAOpe userInfoDAOpe;

    public VideoPlayDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }

    public UserInfoDAOpe getUserInfoDAOpe() {
        return userInfoDAOpe;
    }
}
