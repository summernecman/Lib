package com.siweisoft.service.netdb.agree;

//by summer on 17-09-12.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;

public class AgreeOpe extends BaseDAOpe implements AgreeI {


    public AgreeOpe(Context context) {
        super(context);
    }

    @Override
    public void addAgree(AgreeBean agree, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/agree/addAgree", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                AgreeBean bean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AgreeBean.class);
                onFinishListener.onFinish(bean);
            }
        });
    }

    @Override
    public void cancleAgree(AgreeBean agree, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/agree/cancleAgree", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                AgreeBean bean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AgreeBean.class);
                onFinishListener.onFinish(bean);
            }
        });
    }

    @Override
    public void getAgreeNum(AgreeBean agree, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/agree/getAgreeNum", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                long l = (long) o.getData();
                int res = (int) l;
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void isAgreeNum(AgreeBean agree, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/agree/isAgreeNum", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                onFinishListener.onFinish((boolean) o.getData());
            }
        });
    }

    @Override
    public void clickAgree(AgreeBean agree, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/agree/clickAgree", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                AgreeNumBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AgreeNumBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }
}
