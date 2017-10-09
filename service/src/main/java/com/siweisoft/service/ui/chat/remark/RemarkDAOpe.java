package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.io.File;
import java.util.ArrayList;

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

//    public void updateVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
//        if (videoI == null) {
//            videoI = new VideoOpe(context.getApplicationContext());
//        }
//        final String ff = videoBean.getFile();
//        File file = new File(ff);
//        String[] ss = file.getName().split("_");
//        LogUtil.E("file.getName()" + file.getName());
//        final String s = UrlConstant.fileUrl + "/" + ss[1] + "/" + file.getName();
//        videoBean.setFile(s);
//        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
//        videoI.addVideo(videoBean, new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                videoBean.setFile(ff);
//                onFinishListener.onFinish(o);
//                EMChatOpe.sendCmdMsg(videoBean.getOtherUser().getPhone(), s);
//                videoI.updateVideo(videoBean, new OnFinishListener() {
//                    @Override
//                    public void onFinish(Object o) {
//                        ArrayList<String> strs = (ArrayList<String>) o;
//                        if (strs != null && strs.size() > 0) {
//                            VideoBean v = new VideoBean();
//                            v.setFile(s);
//                            videoI.setVideoUploaded(v, new OnFinishListener() {
//                                @Override
//                                public void onFinish(Object o) {
//
//                                }
//                            });
//                        }
//                    }
//                });
//            }
//        });
//    }


    public void updateVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }
        final String ff = videoBean.getFile();
        File file = new File(ff);
        String[] ss = file.getName().split("_");
        LogUtil.E("file.getName()" + file.getName());
        final String s = UrlConstant.fileUrl + "/" + ss[1] + "/" + file.getName();
        videoBean.setFile(s);
        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));

        videoI.updateVideoById(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(ff);
                onFinishListener.onFinish(videoBean);
                //EMChatOpe.sendCmdMsg(videoBean.getOtherUser().getPhone(), s);
//                videoI.updateVideo(videoBean, new OnFinishListener() {
//                    @Override
//                    public void onFinish(Object o) {
//                        ArrayList<String> strs = (ArrayList<String>) o;
//                        if (strs != null && strs.size() > 0) {
//                            VideoBean v = new VideoBean();
//                            v.setFile(s);
//                            videoI.setVideoUploaded(v, new OnFinishListener() {
//                                @Override
//                                public void onFinish(Object o) {
//
//                                }
//                            });
//                        }
//                    }
//                });
            }
        });
    }

    public void uploadVideo(VideoBean videoBean) {
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }

        final String ff = videoBean.getFile();
        File file = new File(ff);
        String[] ss = file.getName().split("_");
        LogUtil.E("file.getName()" + file.getName());
        final String s = UrlConstant.fileUrl + "/" + ss[1] + "/" + file.getName();

        videoI.updateVideo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<String> strs = (ArrayList<String>) o;
                if (strs != null && strs.size() > 0) {
                    VideoBean v = new VideoBean();
                    v.setFile(s);
                    videoI.setVideoUploaded(v, new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {

                        }
                    });
                }
            }
        });
    }

    public UserBean getOtherUser(VideoBean videoBean) {
        if (Value.userBean.getPhone().equals(videoBean.getFromUser().getPhone())) {
            return videoBean.getToUser();
        }
        return videoBean.getFromUser();
    }
}
