package com.siweisoft.service.ui.main;

//by summer on 2017-07-07.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.bairuitech.anychat.AnyChatCoreSDK;
import com.bairuitech.anychat.AnyChatRecordEvent;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;

import org.greenrobot.eventbus.EventBus;

public class AnyChatRecordEventImp extends BaseDAOpe implements AnyChatRecordEvent {


    VideoI videoI;

    public AnyChatRecordEventImp(Context context) {
        super(context);
        videoI = new VideoOpe(context);
    }

    @Override
    public void OnAnyChatRecordEvent(int dwUserId, int dwErrorCode, String lpFileName, int dwElapse, int dwFlags, int dwParam, String lpUserStr) {
        VideoBean videoBean = GsonUtil.getInstance().fromJson(lpUserStr, VideoBean.class);
        ToastUtil.getInstance().showLong(context, "@dwUserId:" + dwUserId + "@lpFileName:" + lpFileName + "@dwElapse:" + dwElapse + "@dwFlags:" + dwFlags + "@dwParam:" + dwParam + "@lpUserStr" + lpUserStr);
        LogUtil.E("OnAnyChatRecordEvent:" + dwUserId + "@" + dwErrorCode + "@" + lpFileName + "@" + dwElapse + "@" + dwFlags + "@" + dwParam + "@" + lpUserStr);
        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        videoBean.setFile(lpFileName);
        videoBean.setTimenum(dwElapse);
        // videoBean.setToid();
        videoI.addVideo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {

            }
        });
        AnyChatCoreSDK.getInstance(context).SendTextMessage(dwUserId, 1, lpFileName);


        CommentBean commentBean = new CommentBean();
        commentBean.setVideoname(lpFileName);
        EventBus.getDefault().post(commentBean);
    }

    @Override
    public void OnAnyChatSnapShotEvent(int dwUserId, int dwErrorCode, String lpFileName, int dwFlags, int dwParam, String lpUserStr) {

    }
}
