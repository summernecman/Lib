package com.siweisoft.service.netdb.videocomment;

//by summer on 2017-11-09.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.netdb.video.VideoBean;

public interface VideoCommentI {

    public void addVideoComment(VideoCommentBean v, OnFinishListener listener);

    public void getVideoCommentByCallId(VideoBean v, OnFinishListener listener);

    public void getVideoCommentByType(VideoCommentBean v, OnFinishListener listener);

    public void getVideoCommentByTxt(VideoCommentBean v, OnFinishListener listener);
}
