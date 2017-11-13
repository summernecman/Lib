package com.siweisoft.service.netdb.videodetail;

//by summer on 2017-11-08.

import com.android.lib.base.interf.OnFinishListener;

public interface VideoDetailI {

    public void insertVideo(VideoDetailBean v, OnFinishListener onFinishListener);

    public void updateUpload(VideoDetailBean v, OnFinishListener onFinishListener);

    public void getCommentToType(VideoDetailBean v, OnFinishListener onFinishListener);
}
