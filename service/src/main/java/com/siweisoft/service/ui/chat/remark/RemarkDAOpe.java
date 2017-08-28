package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class RemarkDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;

    VideoBean videoBean;


    public RemarkDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
    }

    public UserInfoDAOpe getUserInfoDAOpe() {
        return userInfoDAOpe;
    }

    public void setUserInfoDAOpe(UserInfoDAOpe userInfoDAOpe) {
        this.userInfoDAOpe = userInfoDAOpe;
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }
}
