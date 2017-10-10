package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.system.SystemUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import butterknife.OnClick;

public class ReceiptFrag extends BaseServerFrag<ReceiptUIOpe, ReceiptDAOpe> {

    Vibrator mVibrator;

    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(Value.DATA_DATA));
        getP().getU().initCallINfo(getP().getD().getVideoBean().getFromUser());
        getP().getU().shark();
        if (SystemUtil.isBackground(activity)) {
            IntentUtil.getInstance().IntentTo(activity, activity.getPackageName());
        }
        mVibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(new long[]{1000, 2000, 1000, 2000}, 0);
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

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        switch ((EMCallStateChangeListener.CallState) (event.data)) {
            case DISCONNECTED:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVibrator.cancel();
    }
}
