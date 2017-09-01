package com.siweisoft.service.netdb.comment;

//by summer on 17-08-29.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentOpe extends BaseDAOpe implements CommentI {


    public CommentOpe(Context context) {
        super(context);
    }

    @Override
    public void addComment(CommentBean commentBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(commentBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/commentVideos", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                CommentBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CommentBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getCommentByUserName(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/comment/getCommentByUserName", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUserTips(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/comment/getUserTips", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                HashMap<Integer, TipBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<HashMap<Integer, TipBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });

    }
}
