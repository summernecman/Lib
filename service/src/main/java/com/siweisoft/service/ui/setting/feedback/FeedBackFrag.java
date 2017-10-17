package com.siweisoft.service.ui.setting.feedback;

//by summer on 17-08-28.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.UriUtils;
import com.android.lib.util.data.DateFormatUtil;
import com.hedgehog.ratingbar.RatingBar;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.feedback.FeedBackBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.Image.ImageFrag;

public class FeedBackFrag extends BaseServerFrag<FeedBAckUIOpe, FeedBackDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "意见反馈", "", "确定"));
        getP().getU().initPics(getP().getD().getPics(), this);
        getP().getU().initRating(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                getP().getD().setRate(RatingCount);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                FeedBackBean feedBackBean = new FeedBackBean();
                feedBackBean.setCreate(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
                feedBackBean.setUserid(Value.getUserInfo().getId());
                feedBackBean.setRate(getP().getD().getRate());
                feedBackBean.setRemark(getP().getU().bind.input.getText().toString());
                getP().getD().sendFeedBack(feedBackBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                    }
                });
        }
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                if (v.getTag(R.id.data) instanceof Integer) {
                    IntentUtil.getInstance().photoShowFromphone(fragment, ValueConstant.CODE_REQUSET3);
                } else {
                    ImageFrag imageFrag = new ImageFrag();
                    imageFrag.setArguments(new Bundle());
                    imageFrag.getArguments().putString(ValueConstant.DATA_DATA, (String) v.getTag(R.id.data));
                    FragmentUtil2.getInstance().add(activity, Value.getNowRoot(), imageFrag);
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        getP().getD().addPic(UriUtils.getPath(activity, data.getData()));
        if (getP().getD().getPics().size() > 9) {
            ToastUtil.getInstance().showShort(activity, "图片有点太多了");
        }
        getP().getU().initPics(getP().getD().getPics(), this);
    }
}
