package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.view.bottommenu.MessageEvent;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.remark.RemarkFrag;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;

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
        //ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
        getP().getD().getUnTypeUserList(Value.userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
                //ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
            }
        });
//        getP().getD().getOtherUsersInfoByPhone(new AllUserBean(Value.userBean,ChatInit.getInstance().getUserList()), new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
//                ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
//            }
//        });

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
                if (userBean.getState() != UserBean.STATE_ONLINE) {
                    return;
                }
                VideoBean videoBean = new VideoBean();
                videoBean.setToUser(userBean);
                videoBean.setFromUser(Value.userBean);
                videoBean.setFromphone(Value.userBean.getPhone());
                videoBean.setTophone(userBean.getPhone());

                RemarkFrag remarkFrag = new RemarkFrag();
                remarkFrag.setArguments(new Bundle());
                remarkFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
                FragmentUtil2.getInstance().add(fragment.getActivity(), Value.ROOTID_TWO, remarkFrag);

                //ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(userBean.getChatid()), 0, 0, 0, GsonUtil.getInstance().toJson(videoBean));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        getP().getD().getUnTypeUserList(Value.userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
                //ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
            }
        });
//        getP().getD().getOtherUsersInfoByPhone(new AllUserBean(Value.userBean,ChatInit.getInstance().getUserList()), new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
//                ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
//            }
//        });
    }
}
