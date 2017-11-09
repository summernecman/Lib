package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.SPUtil;
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
import com.siweisoft.service.ui.user.onlinelist.OnLineListFrag;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class ReceiptFrag extends BaseServerFrag<ReceiptUIOpe, ReceiptDAOpe> {

    Vibrator mVibrator;

    PowerManager.WakeLock mWakeLock;

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

        PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "SimpleTimer");
        mWakeLock.acquire();//这里唤醒锁，用这种方式要记得在适当的地方关闭锁，
        mWakeLock.release();
    }

    @OnClick({R.id.tv_receipt, R.id.tv_refuse})
    public void onClickEvent(View v) {
        SPUtil.getInstance().saveInt(getP().getD().getVideoBean().getFromUser().getPhone(), 0);
        switch (v.getId()) {
            case R.id.tv_receipt:
                getP().getD().updateCallState(getP().getD().getVideoBean(), VideoBean.CALL_STATE_SUCCESS);
                VideoChatFrag videoChatFrag = new VideoChatFrag();
                videoChatFrag.setArguments(new Bundle());
                videoChatFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getArguments().getSerializable(Value.DATA_DATA));
                videoChatFrag.getArguments().putBoolean(Value.DATA_INTENT, true);
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                FragmentUtil2.getInstance().add(activity, Value.FULLSCREEN, videoChatFrag);
                break;
            case R.id.tv_refuse:
                getP().getD().updateCallState(getP().getD().getVideoBean(), VideoBean.CALL_STATE_REJECT);
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
                SPUtil.getInstance().saveInt(
                        getP().getD().getVideoBean().getFromUser().getPhone(),
                        SPUtil.getInstance().getInt(getP().getD().getVideoBean().getFromUser().getPhone()) + 1
                );


                MessageEvent v1 = new MessageEvent();
                v1.sender = ReceiptFrag.class.getName();
                v1.dealer = OnLineListFrag.class.getName();
                v1.data = 1;
                EventBus.getDefault().post(v1);

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
