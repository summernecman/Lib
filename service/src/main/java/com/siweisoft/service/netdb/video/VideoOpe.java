package com.siweisoft.service.netdb.video;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;
import java.util.List;

public class VideoOpe extends BaseDAOpe implements VideoI {


    public VideoOpe(Context context) {
        super(context);
    }

    @Override
    public void getVideos(UserBean userBean, OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/getVideos", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                //UserBean res =GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()),UserBean.class);
                //onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void addVideo(VideoBean videoBean, OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/addVideo", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                //UserBean res =GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()),UserBean.class);
                //onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getHistoryVideos(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/getHistoryVideos", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                List<VideoBean> retList = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<List<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(retList);
            }
        });
    }

    @Override
    public void getVideosByContacts(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/getVideosByContacts", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                ArrayList<ArrayList<VideoBean>> retList = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<ArrayList<VideoBean>>>() {
                }.getType());
                onFinishListener.onFinish(retList);
            }
        });
    }
}
