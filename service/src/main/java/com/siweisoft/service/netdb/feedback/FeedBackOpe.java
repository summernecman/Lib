package com.siweisoft.service.netdb.feedback;

//by summer on 17-09-27.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;

public class FeedBackOpe extends BaseDAOpe implements FeedBackI {


    public FeedBackOpe(Context context) {
        super(context);
    }

    @Override
    public void sendFeedBack(FeedBackBean feedBackBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(feedBackBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/feedback/sendfeedback", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                FeedBackBean videos = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), FeedBackBean.class);
                onFinishListener.onFinish(videos);
            }
        });
    }
}
