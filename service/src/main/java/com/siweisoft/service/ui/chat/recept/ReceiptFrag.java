package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.videochat.chatutil.CallingCenter;
import com.siweisoft.service.videochat.chatutil.ChatInit;

import butterknife.OnClick;

public class ReceiptFrag extends BaseServerFrag<ReceiptUIOpe, ReceiptDAOpe> {


    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setDwUserId(getArguments().getInt(Value.DATA_POSITION));
        getP().getD().setUserStr(getArguments().getString(Value.DATA_DATA2));
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(Value.DATA_DATA));
    }

    @OnClick({R.id.tv_receipt, R.id.tv_refuse})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_receipt:
                LogUtil.E("oncllick" + ChatInit.getInstance().getAnyChatSDK().GetUserName(getP().getD().getDwUserId()) + "向你发来视频请求");
                CallingCenter.getInstance().VideoCallControl(AnyChatDefine.BRAC_VIDEOCALL_EVENT_REPLY, getP().getD().getDwUserId(), AnyChatDefine.BRAC_ERRORCODE_SUCCESS, 0, 0, getP().getD().getUserStr());
                break;
            case R.id.tv_refuse:
                CallingCenter.getInstance().VideoCallControl(AnyChatDefine.ANYCHAT_STREAMPLAY_EVENT_FINISH, Integer.parseInt(getP().getD().getVideoBean().getOtherid()), AnyChatDefine.BRAC_ERRORCODE_SESSION_REFUSE, 0, 0, "");
                break;
        }
        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
    }
}
