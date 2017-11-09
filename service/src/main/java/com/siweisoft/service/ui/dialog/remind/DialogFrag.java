package com.siweisoft.service.ui.dialog.remind;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.system.SystemUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;

import butterknife.OnClick;

public class DialogFrag extends BaseServerFrag<DialogUIOpe, DialogDAOpe> {

    OnFinishListener onFinishListener;

    @Override
    public void doThing() {
        super.doThing();
        if (SystemUtil.isBackground(activity)) {
            IntentUtil.getInstance().IntentTo(activity, activity.getPackageName());
        }
        getP().getU().bind.tvRefuse.setSelected(true);
        getP().getU().bind.tvRefuse.setSelected(false);
    }

    @OnClick({R.id.tv_receipt, R.id.tv_refuse})
    public void onClickEvent(View v) {
        if (onFinishListener != null) {
            onFinishListener.onFinish(v);
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
