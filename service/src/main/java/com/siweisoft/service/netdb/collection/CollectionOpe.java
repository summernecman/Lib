package com.siweisoft.service.netdb.collection;

//by summer on 17-09-04.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.login.UserBean;

public class CollectionOpe extends BaseDAOpe implements CollectionI {


    public CollectionOpe(Context context) {
        super(context);
    }

    @Override
    public void getCollectionVideosByUserId(UserBean userBean, OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/getCollectionVideosByUserId", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(o);
            }
        });
    }

    @Override
    public void collect(VideoBean videoBean, OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/collect", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {

            }
        });
    }
}
