package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.video.VideoI;
import com.siweisoft.service.netdb.video.VideoOpe;
import com.siweisoft.service.ui.Constant.Value;

public class VideoRecordDAOpe extends BaseDAOpe {


    VideoI videoI = new VideoOpe(context);


    public VideoRecordDAOpe(Context context) {
        super(context);
    }

    public void getHistory(OnFinishListener onFinishListener) {

        videoI.getHistoryVideos(Value.userBean, onFinishListener);
    }
}
