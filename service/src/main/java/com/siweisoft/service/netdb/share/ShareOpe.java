package com.siweisoft.service.netdb.share;

//by summer on 17-09-06.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

public class ShareOpe extends BaseDAOpe implements ShareI {

    public ShareOpe(Context context) {
        super(context);
    }

    @Override
    public void share(final ShareBean shareBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(shareBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/shark/shark", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ShareBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), ShareBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getShareNumByUserPhone(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/shark/getShareNumByUserPhone", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                double r = (double) o.getData();
                int res = (int) r;
                onFinishListener.onFinish(res + "");
            }
        });
    }

    @Override
    public void getSharesByReceipt(ShareBean shareBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(shareBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/shark/getSharesByReceipt", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<VideoBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }
}
