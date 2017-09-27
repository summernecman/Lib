package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.system.SystemUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;
import com.siweisoft.service.ui.chat.videochat.VideoChatMsg;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class ReceiptFrag extends BaseServerFrag<ReceiptUIOpe, ReceiptDAOpe> {


    @Override
    public void doThing() {
        super.doThing();
        if (SystemUtil.isBackground(activity)) {
            IntentUtil.getInstance().IntentTo(activity, activity.getPackageName());
        }
    }

    @OnClick({R.id.tv_receipt, R.id.tv_refuse})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_receipt:
                VideoChatFrag videoChatFrag = new VideoChatFrag();
                videoChatFrag.setArguments(new Bundle());
                videoChatFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getArguments().getSerializable(Value.DATA_DATA));
                videoChatFrag.getArguments().putBoolean(Value.DATA_INTENT, true);
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                FragmentUtil2.getInstance().add(activity, Value.FULLSCREEN, videoChatFrag);
                break;
            case R.id.tv_refuse:
                try {
                    EMClient.getInstance().callManager().rejectCall();
                } catch (EMNoActiveCallException e) {
                    e.printStackTrace();
                }
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(VideoChatMsg msg) {
        switch (msg.code) {
            case VideoChatMsg.CODE_END_RECORD:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                break;
        }

    }


}
