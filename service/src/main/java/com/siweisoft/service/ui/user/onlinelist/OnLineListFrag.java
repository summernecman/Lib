package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import java.util.ArrayList;

public class OnLineListFrag extends BaseServerFrag<OnLineListUIOpe, OnLineListDAOpe> {


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
        setTitleBean(new TitleBean("", "联系人", ""));
        getP().getU().initList(ChatInit.getInstance().getUserList(), this);
        ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
        getP().getD().getUsersInfoByPhone(ChatInit.getInstance().getUserList(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
                ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final UserBean userBean = (UserBean) v.getTag(R.id.data);
        switch (v.getId()) {
            case R.id.iv_head:
                UserInfoFrag userInfoFrag = new UserInfoFrag();
                userInfoFrag.setArguments(new Bundle());
                userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, userBean);
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_TWO, userInfoFrag);
                break;
            case R.id.iv_call:
                VideoBean videoBean = new VideoBean();
                videoBean.setFromphone(Value.userBean.getPhone());
                videoBean.setTophone(ChatInit.getInstance().getAnyChatSDK().GetUserName(Integer.parseInt(userBean.getChatid())));
                ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(userBean.getChatid()), 0, 0, 0, GsonUtil.getInstance().toJson(videoBean));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ChatInit.getInstance().leaveRoom(VideoValue.URL.ROOMID);
        ChatInit.getInstance().doLoginOut();
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        getP().getD().getUsersInfoByPhone(ChatInit.getInstance().getUserList(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
                ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
            }
        });
    }
}
