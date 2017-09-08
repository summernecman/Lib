package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

public class RemarkDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;

    VideoBean videoBean;

    VideoI videoI;

    float ratingbar;

    TipsBean tipsBean;

    UserI userI;


    public RemarkDAOpe(Context context) {
        super(context);
        userInfoDAOpe = new UserInfoDAOpe(context);
        videoI = new VideoOpe(context);
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

    public TipsBean getData() {
        if (tipsBean == null) {
            tipsBean = userInfoDAOpe.getData();
        }
        return tipsBean;
    }

    public TipsBean getTipsBean() {
        return tipsBean;
    }

    public void getChatUserInfo(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getUserInfoByPhone(userBean, onFinishListener);
    }
}
