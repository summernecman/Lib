package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.view.View;
import android.widget.TextView;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.agree.AgreeBean;
import com.siweisoft.service.netdb.agree.AgreeNumBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;

public class UserInfoFrag extends BaseServerFrag<UserInfoUIOpe, UserInfoDAOpe> implements ViewListener {


    @Override
    public void doThing() {
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
                materialRefreshLayout.finishRefreshingDelay();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        setTitleBean(new TitleBean("返回", "信息", ""));
        getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().getUserCenterDAOpe().getUserTips(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initTips((HashMap<Integer, TipBean>) o);
            }
        });
        getP().getD().getRemarks(getP().getD().getCommentReq(Value.userBean, getP().getD().getUserBean()), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().setCommentBeen((ArrayList<CommentBean>) o);
                getP().getU().initRemarks(getP().getD().getCommentBeen(), UserInfoFrag.this);
            }
        });

        getP().getD().getUserCallInfo(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initCallInfo((VideoTimeBean) o);
            }
        });
        getP().getU().initHead(getP().getD().getUserBean());

        getP().getD().getUserRateIfNull(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getUserBean().setAvg((Float) o);
            }
        });
    }

    @OnClick({R.id.call})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.call:
                UserBean userBean = getP().getD().getUserChatInfo(getP().getD().getUserBean());
                if (userBean != null) {
                    VideoBean videoBean = new VideoBean();
                    videoBean.setFromphone(Value.userBean.getPhone());
                    videoBean.setTophone(ChatInit.getInstance().getAnyChatSDK().GetUserName(Integer.parseInt(userBean.getChatid())));
                    videoBean.setToUser(getP().getD().getUserBean());
                    videoBean.setFromUser(Value.userBean);
                    ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(userBean.getChatid()), 0, 0, 0, GsonUtil.getInstance().toJson(videoBean));
                } else {
                    ToastUtil.getInstance().showShort(activity, "用户不在线");
                }
                break;
        }
    }

    @Override
    public void onInterupt(int type, final View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()) {
                    case R.id.iv_agree:
                        final CommentBean commentBean = (CommentBean) v.getTag(R.id.data);
                        AgreeBean agreeBean = new AgreeBean();
                        agreeBean.setCommentid(commentBean.getId());
                        agreeBean.setAgreeid(Value.userBean.getId());
                        getP().getD().clickAgree(agreeBean, new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                AgreeNumBean res = (AgreeNumBean) o;
                                v.setSelected(res.isAgree());
                                TextView textView = (TextView) v.getTag(R.id.data1);
                                textView.setText(StringUtil.getStr(res.getAgreenum()));
                                commentBean.setAgree(res.isAgree());
                                commentBean.setAgreeNum(res.getAgreenum());
                            }
                        });
                        break;
                }
                break;
        }
    }
}
