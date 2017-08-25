package com.siweisoft.service.ui.main;

//by summer on 2017-07-07.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.bairuitech.anychat.AnyChatRecordEvent;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;

public class AnyChatRecordEventImp extends BaseDAOpe implements AnyChatRecordEvent {


    VideoI videoI;

    public AnyChatRecordEventImp(Context context) {
        super(context);
        videoI = new VideoOpe(context);
    }

    @Override
    public void OnAnyChatRecordEvent(int dwUserId, int dwErrorCode, String lpFileName, int dwElapse, int dwFlags, int dwParam, String lpUserStr) {
        ToastUtil.getInstance().showLong(context, "@dwUserId:" + dwUserId + "@lpFileName:" + lpFileName + "@dwElapse:" + dwElapse + "@dwFlags:" + dwFlags + "@dwParam:" + dwParam + "@lpUserStr" + lpUserStr);
        LogUtil.E("OnAnyChatRecordEvent:" + dwUserId + "-0-" + dwErrorCode + "-0-" + lpFileName + "-0-" + dwElapse + "-0-" + dwFlags + "-0-" + dwParam + "-0-" + lpUserStr);
        VideoBean videoBean = new VideoBean();
        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        videoBean.setFile(lpFileName);
        videoBean.setTophone(Value.userBean.getPhone());
        videoBean.setFromphone(lpUserStr);
        // videoBean.setToid();
        videoI.addVideo(videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                LogUtil.E(o);
            }
        });

        videoI.getVideos(Value.userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                LogUtil.E(o);
            }
        });

    }

    @Override
    public void OnAnyChatSnapShotEvent(int dwUserId, int dwErrorCode, String lpFileName, int dwFlags, int dwParam, String lpUserStr) {

    }
}
