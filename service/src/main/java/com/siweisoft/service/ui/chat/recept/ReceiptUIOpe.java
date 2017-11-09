package com.siweisoft.service.ui.chat.recept;

//by summer on 17-09-11.

import android.content.Context;
import android.view.animation.LinearInterpolator;

import com.android.lib.base.ope.BaseUIOpe;
import com.github.florent37.viewanimator.ViewAnimator;
import com.siweisoft.service.databinding.FragReceiptBinding;
import com.siweisoft.service.netdb.user.UserBean;

public class ReceiptUIOpe extends BaseUIOpe<FragReceiptBinding> {


    public ReceiptUIOpe(Context context) {
        super(context);
    }

    public void initCallINfo(UserBean userBean) {
        bind.tvUserinfo.setText(userBean.getPhone() + "(" + userBean.getName() + ") 发起视频通话");
    }

    public void shark() {
        ViewAnimator.animate(bind.tvReceipt).shake().interpolator(new LinearInterpolator()).duration(10000).start();
        ViewAnimator.animate(bind.tvRefuse).shake().interpolator(new LinearInterpolator()).duration(10000).start();
    }


}
