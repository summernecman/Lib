package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.ToastUtil;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;

public class UserInfoFrag extends BaseServerFrag<UserInfoUIOpe, UserInfoDAOpe> {

    @Override
    public void doThing() {
        setTitleBean(new TitleBean("返回", "信息", ""));
        getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().getUserCenterDAOpe().getUserTips(getP().getD().getUserBean(), new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                getP().getU().initTips((HashMap<Integer, TipBean>) o);
                            }
                        });
        getP().getD().getRemarks(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initRemarks((ArrayList<CommentBean>) o);
            }
        });

        getP().getD().getUserCallInfo(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initCallInfo((VideoTimeBean) o);
            }
        });
        getP().getU().initHead(getP().getD().getUserBean());
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
                    ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(userBean.getChatid()), 0, 0, 0, GsonUtil.getInstance().toJson(videoBean));
                } else {
                    ToastUtil.getInstance().showShort(activity, "用户不在线");
                }
                break;
        }
    }
}
