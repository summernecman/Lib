package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.netdb.tip.TipOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.netdb.videocomment.VideoCommentBean;
import com.siweisoft.service.netdb.videocomment.VideoCommentOpe;
import com.siweisoft.service.netdb.videodetail.VideoDetailBean;
import com.siweisoft.service.netdb.videodetail.VideoDetailOpe;
import com.siweisoft.service.netdb.videotip.VideoTipBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.io.File;
import java.util.ArrayList;

public class RemarkDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;

    VideoBean videoBean;

    VideoI videoI;

    private float ratingbar = 5f;

    TipsBean tipsBean;

    UserI userI;

    TipOpe tipOpe;

    VideoDetailOpe videoDetailI;

    VideoCommentOpe videoCommentI;

    private VideoTipBean videoTipBean;

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

    public TipsBean getTipsBean() {
        return tipsBean;
    }

    public void setTipsBean(TipsBean tipsBean) {
        this.tipsBean = tipsBean;
    }

    public void getChatUserInfo(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        userI.getUserInfoByPhone(userBean, onFinishListener);
    }

    public void getTips(OnFinishListener onFinishListener) {
        if (tipOpe == null) {
            tipOpe = new TipOpe(context);
        }
        tipOpe.getTips(onFinishListener);
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


    public float getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(float ratingbar) {
        this.ratingbar = ratingbar;
    }

    public static void renameFile(VideoBean videoBean) {
        File file = new File(videoBean.getFile());
        if (file.exists()) {
            String s = (System.currentTimeMillis() + "");
            File f = new File(file.getParent(), DateFormatUtil.getNowStr(DateFormatUtil.YYYY__MM__DD__HH__MM__SS) + s.substring(s.length() - 3, s.length()) + "_" + videoBean.getFromUser().getId() + "to" + videoBean.getToUser().getId() + ".mov");
            file.renameTo(f);
            videoBean.setFile(f.getPath());
        }
    }

    public void updateVideoCallTimeNum(VideoBean videoBean) {
        if (videoI == null) {
            videoI = new VideoOpe(context);
        }
        videoI.updateVideoCallTimeNum(videoBean, null);
    }

    public void insetVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        if (videoDetailI == null) {
            videoDetailI = new VideoDetailOpe(context);
        }
        final String ff = videoBean.getFile();
        final VideoDetailBean v = new VideoDetailBean();
        v.setCallid(videoBean.getId());
        v.setUrl(getUrlFromLocal(videoBean.getFile()));
        v.setUploaded(0);
        v.setUserid(Value.getUserInfo().getId());
        v.setTime(videoBean.getTimenum());
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            videoBean.setIsfrom(true);
        } else {
            videoBean.setIsfrom(false);
        }

        videoDetailI.insertVideo(v, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(ff);
                onFinishListener.onFinish(v);
            }
        });
    }

    public void addVideoComment(VideoCommentBean v, OnFinishListener listener) {
        if (videoCommentI == null) {
            videoCommentI = new VideoCommentOpe(context);
        }
        videoCommentI.addVideoComment(v, listener);
    }

    public boolean isRecord() {
        return !NullUtil.isStrEmpty(getVideoBean().getFile());
    }



    public String getUrlFromLocal(String ff) {
        File file = new File(ff);
        String ss = file.getName().substring(0, "20170101".length());
        LogUtil.E("file.getName()" + file.getName());
        final String s = UrlConstant.fileUrl + "/" + ss + "/" + file.getName();
        return s;
    }

    public void updateVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }
        final String ff = videoBean.getFile();
        final String s = getUrlFromLocal(ff);
        videoBean.setFile(s);
        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            videoBean.setIsfrom(true);
        } else {
            videoBean.setIsfrom(false);
        }
        videoI.updateVideoById(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(ff);
                onFinishListener.onFinish(videoBean);
            }
        });
    }

    public void uploadVideo(VideoBean videoBean, final VideoDetailBean vv) {
        if (videoI == null) {
            videoI = new VideoOpe(context.getApplicationContext());
        }

        final String ff = videoBean.getFile();

        videoI.updateVideo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<String> strs = (ArrayList<String>) o;
                if (strs != null && strs.size() > 0) {
                    if (videoDetailI == null) {
                        videoDetailI = new VideoDetailOpe(context);
                    }
                    videoDetailI.updateUpload(vv, null);

                }
            }
        }, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {

            }
        });
    }

    public UserBean getOtherUser(VideoBean videoBean) {
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            return videoBean.getToUser();
        }
        return videoBean.getFromUser();
    }

    public VideoTipBean getVideoTipBean() {
        return videoTipBean;
    }

    public void setVideoTipBean(VideoTipBean videoTipBean) {
        this.videoTipBean = videoTipBean;
    }
}
