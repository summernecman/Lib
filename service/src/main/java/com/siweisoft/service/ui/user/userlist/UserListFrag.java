package com.siweisoft.service.ui.user.userlist;

//by summer on 2017-07-04.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.UserReqBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.main.RoleInfo;
import com.siweisoft.service.ui.record.record.RecordFrag;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import butterknife.OnClick;

public class UserListFrag extends BaseUIFrag<UserListUIOpe, UserListDAOpe> {


    public static Fragment getFragment(int userid) {
        Fragment fragment = new UserListFrag();
        fragment.setArguments(new Bundle());
        fragment.getArguments().putInt(ValueConstant.DATA_DATA, userid);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        P().U().initList(ChatInit.getInstance().getUserList(getArguments().getInt(ValueConstant.DATA_DATA)), this);
        ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final RoleInfo roleInfo = (RoleInfo) v.getTag(R.id.data);
        if (roleInfo.getName().equals(Value.userInfo.name.get())) {
            ToastUtil.getInstance().showShort(activity, "这是你自己");
            return;
        }
        P().D().getUserI().getUserInfo(roleInfo.getName(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if (o != null) {
                    UserReqBean userReqBean = (UserReqBean) o;
                    P().D().setRoleInfo(roleInfo);
                    boolean is = userReqBean.getIscustomer() == 0 ? false : true;
                    if (is == Value.userInfo.type.get()) {
                        ToastUtil.getInstance().showShort(activity, "客服之间不能视频");
                    } else {
                        ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(roleInfo.getUserID()), 0, 0, 0, "");
                    }
                }
            }
        });
    }

    @OnClick({R.id.recordtv})
    public void recordFile(View v) {
        FragmentUtil.getInstance().add(activity, Value.ROOTID, new RecordFrag());
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
        P().U().initList(ChatInit.getInstance().getUserList(getArguments().getInt(ValueConstant.DATA_DATA)), this);
        ChatInit.getInstance().getAnyChatSDK().SetVideoCallEvent(new AnyChatVideoCallEventImp(fragment));
    }
}
