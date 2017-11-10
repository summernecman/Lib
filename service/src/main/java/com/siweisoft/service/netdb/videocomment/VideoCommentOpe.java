package com.siweisoft.service.netdb.videocomment;

//by summer on 2017-11-09.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.netadapter.UINetAdapter;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.siweisoft.service.netdb.video.VideoBean;

public class VideoCommentOpe extends BaseDAOpe implements VideoCommentI {

    public VideoCommentOpe(Context context) {
        super(context);
    }

    @Override
    public void addVideoComment(VideoCommentBean v, final OnFinishListener listener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(v));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/videocomment/addVideoComment", baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (listener != null) {
                    listener.onFinish(o);
                }
            }
        });
    }

    @Override
    public void getVideoCommentByCallId(VideoBean v, OnFinishListener listener) {

    }

    @Override
    public void getVideoCommentByType(VideoCommentBean v, OnFinishListener listener) {

    }

    @Override
    public void getVideoCommentByTxt(VideoCommentBean v, OnFinishListener listener) {

    }
}
