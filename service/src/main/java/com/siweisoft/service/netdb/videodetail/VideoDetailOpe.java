package com.siweisoft.service.netdb.videodetail;

//by summer on 2017-11-08.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.siweisoft.service.netdb.user.UserBean;

public class VideoDetailOpe extends BaseDAOpe implements VideoDetailI {

    public VideoDetailOpe(Context context) {
        super(context);
    }

    @Override
    public void insertVideo(VideoDetailBean v, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(v));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/videodetail/insertvideo", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(o);
                }

            }
        });
    }

    @Override
    public void updateUpload(VideoDetailBean v, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(v));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/videodetail/updateUpload", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(o);
                }
            }
        });
    }

    @Override
    public void getCommentToType(VideoDetailBean v, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(v));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/videodetail/getCommentToType", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (onFinishListener != null) {
                    UserBean userBean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(userBean);
                }
            }
        });
    }
}
