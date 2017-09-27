package com.siweisoft.service.netdb.feedback;

//by summer on 17-09-27.

import com.android.lib.base.interf.OnFinishListener;

public interface FeedBackI {

    public void sendFeedBack(FeedBackBean feedBackBean, OnFinishListener onFinishListener);
}
