package com.siweisoft.service.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.recept.ReceiptFrag;

public class CallReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // 拨打方username
        String from = intent.getStringExtra("from");
        // call type
        String type = intent.getStringExtra("type");

        LogUtil.E(from + "" + type);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(EMClient.getInstance().callManager().getCurrentCallSession().getExt(), VideoBean.class);

        ReceiptFrag receiptFrag = new ReceiptFrag();
        receiptFrag.setArguments(new Bundle());
        receiptFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
        FragmentUtil2.getInstance().add((FragmentActivity) context, Value.FULLSCREEN, receiptFrag);

    }
}