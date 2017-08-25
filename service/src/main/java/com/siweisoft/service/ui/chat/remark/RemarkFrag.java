package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.videochat.chatutil.ChatInit;

public class RemarkFrag extends BaseUIFrag<RemarkUIOpe, RemarkDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        ChatInit.getInstance().getAnyChatSDK().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST, Integer.parseInt(getP().getD().getUserBean().getChatid()), 0, 0, 0, "");
    }

    @Override
    public void doThing() {
        getP().getU().initTips(getP().getD().userInfoDAOpe.getData());
    }

}
