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
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class CollectionOpe extends BaseDAOpe implements CollectionI {


    public CollectionOpe(Context context) {
        super(context);
    }

    @Override
    public void getCollectionVideosByUserId(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/getCollectionVideosByUserId", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<VideoBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void isCollectedByVideoIdAndUserId(final CollectionBean collectionBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(collectionBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/isCollectedByVideoIdAndUserId", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (o.getData() == null) {
                    onFinishListener.onFinish(o.getData());
                } else {
                    CollectionBean collectionBean1 = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CollectionBean.class);
                    onFinishListener.onFinish(collectionBean1);
                }


            }
        });
    }

    @Override
    public void getCollectionNumByUserId(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/getCollectionNumByUserId", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                double res = (double) o.getData();
                int r = (int) res;
                onFinishListener.onFinish(r + "");
            }
        });
    }


    @Override
    public void collect(CollectionBean collectionBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(collectionBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/collect", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (!b) {
                    onFinishListener.onFinish(b);
                    return;
                }
                CollectionBean collectionBean1 = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CollectionBean.class);
                onFinishListener.onFinish(collectionBean1);
            }
        });
    }

    @Override
    public void disCollect(CollectionBean collectionBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(collectionBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/collection/discollect", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                onFinishListener.onFinish(b);
            }
        });
    }
}
