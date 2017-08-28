package com.siweisoft.service.ui.user.userlist;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class UserListFrag extends BaseUIFrag<UserListUIOpe, UserListDAOpe> {



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().initList(ChatInit.getInstance().getUserList(), this);
        ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_head:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_TWO, new UserInfoFrag());
                break;
            default:
                final UserBean userBean = (UserBean) v.getTag(R.id.data);
                if (userBean.getChatid().equals(Value.userBean.getChatid())) {
                    ToastUtil.getInstance().showShort(activity, "这是你自己");
                    return;
                }
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
        getP().getU().initList(ChatInit.getInstance().getUserList(), this);
        ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
    }
}
