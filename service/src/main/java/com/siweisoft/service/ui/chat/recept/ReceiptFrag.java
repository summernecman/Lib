package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.remark.RemarkFrag;

import butterknife.OnClick;

public class ReceiptFrag extends BaseServerFrag<ReceiptUIOpe, ReceiptDAOpe> {



    @OnClick({R.id.tv_receipt, R.id.tv_refuse})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_receipt:
                RemarkFrag remarkFrag = new RemarkFrag();
                remarkFrag.setArguments(new Bundle());
                remarkFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getArguments().getSerializable(Value.DATA_DATA));
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_TWO, remarkFrag);
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
}
