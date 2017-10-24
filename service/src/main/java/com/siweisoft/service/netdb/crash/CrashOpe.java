package com.siweisoft.service.netdb.crash;

//by summer on 17-09-08.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;

public class CrashOpe extends BaseDAOpe implements CrashI {

    public CrashOpe(Context context) {
        super(context);
    }

    @Override
    public void sendCrash(CrashBean crashBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(crashBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/crash/sendCrash", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                CrashBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CrashBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }
}
