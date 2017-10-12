package com.siweisoft.service.netdb.tip;

//by summer on 17-10-12.

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
import com.siweisoft.service.bean.TipsBean;

import java.util.ArrayList;

public class TipOpe extends BaseDAOpe implements TipI {

    public TipOpe(Context context) {
        super(context);
    }

    @Override
    public void getTips(final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/tip/gettips", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<TipBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<TipBean>>() {
                }.getType());
                TipsBean tipsBean = new TipsBean();
                tipsBean.setTipBeen(res);
                onFinishListener.onFinish(tipsBean);
            }
        });
    }
}
