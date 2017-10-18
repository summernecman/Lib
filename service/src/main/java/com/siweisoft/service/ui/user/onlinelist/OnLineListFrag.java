package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;

import java.util.ArrayList;
import java.util.List;

public class OnLineListFrag extends BaseServerFrag<OnLineListUIOpe, OnLineListDAOpe> {


    @Override
    public void doThing() {
        activity.findViewById(R.id.ftv_right2).setOnClickListener(this);
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData2();
                materialRefreshLayout.finishRefreshingDelay();
            }
        });

        setTitleBean(new TitleBean("", "联系人", "", ""));
        getP().getU().initList(new ArrayList<UserBean>(), null);

        EMClient.getInstance().chatroomManager().asyncFetchPublicChatRoomsFromServer(1, null, new EMValueCallBack<EMCursorResult<EMChatRoom>>() {
            @Override
            public void onSuccess(EMCursorResult<EMChatRoom> value) {
                List<EMChatRoom> rooms = value.getData();
                for (int i = 0; i < rooms.size(); i++) {
                    Value.saveRoom(rooms.get(i));
                    getP().getD().setEmChatRoom(rooms.get(i));
                    LogUtil.E(rooms.get(i).getId() + "" + rooms.get(i).getName());
                    EMClient.getInstance().chatroomManager().joinChatRoom(rooms.get(i).getId(), new EMValueCallBack<EMChatRoom>() {
                        @Override
                        public void onSuccess(EMChatRoom value) {
                            EMClient.getInstance().chatroomManager().addChatRoomChangeListener(getP().getD().getOnlineChangeListener());
                            initData2();
                        }

                        @Override
                        public void onError(final int error, String errorMsg) {
                            //加入聊天室失败
                        }
                    });
                }
            }

            @Override
            public void onError(int error, String errorMsg) {

            }
        });

    }


    public void initData2() {
        if (getP().getD().getEmChatRoom() == null) {
            return;
        }
        EMClient.getInstance().chatroomManager().asyncFetchChatRoomMembers(getP().getD().getEmChatRoom().getId(), null, 100, new EMValueCallBack<EMCursorResult<String>>() {
            @Override
            public void onSuccess(EMCursorResult<String> value) {
                getP().getD().getOtherUsersInfoByPhone(value.getData(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        getP().getD().setOnlines((ArrayList<UserBean>) o);
                        getP().getU().initList(getP().getD().getOnlines(), OnLineListFrag.this);
                    }
                });
            }

            @Override
            public void onError(int error, String errorMsg) {

            }
        });


        //ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
//        getP().getD().getUnTypeUserList(Value.getUserInfo(), new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
//                //ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
//            }
//        });
//        getP().getD().getOtherUsersInfoByPhone(new AllUserBean(Value.getUserInfo(),ChatInit.getInstance().getUserList()), new OnFinishListener() {
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
        v.setEnabled(false);
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
                videoBean.setFromUser(Value.getUserInfo());
                videoBean.setFromphone(Value.getUserInfo().getPhone());
                videoBean.setTophone(userBean.getPhone());

                VideoChatFrag videoChatFrag = new VideoChatFrag();
                videoChatFrag.setArguments(new Bundle());
                videoChatFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
                FragmentUtil2.getInstance().add(fragment.getActivity(), Value.FULLSCREEN, videoChatFrag);

                //ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(userBean.getChatid()), 0, 0, 0, GsonUtil.getInstance().toJson(videoBean));
                break;
            case R.id.ftv_right2:
                //FragmentUtil2.getInstance().add(activity, Value.ROOTID_TWO, new CollecFrag());
                break;
        }
        v.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatroomManager().removeChatRoomListener(getP().getD().getOnlineChangeListener());
        if (getP().getD().getEmChatRoom() == null) {
            return;
        }
        EMClient.getInstance().chatroomManager().leaveChatRoom(getP().getD().getEmChatRoom().getId());
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        if (event.data instanceof Integer) {
            getP().getU().refresh();
        } else {
            initData2();
        }
    }
}
