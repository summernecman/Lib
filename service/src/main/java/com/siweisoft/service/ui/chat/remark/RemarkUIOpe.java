package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.hedgehog.ratingbar.RatingBar;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.databinding.FragRemarkBinding;

import java.util.ArrayList;

public class RemarkUIOpe extends BaseUIOpe<FragRemarkBinding> {

    public RemarkUIOpe(Context context) {
        super(context);

    }

    public void initRatingBar(final OnFinishListener onFinishListener) {
        bind.ratingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                onFinishListener.onFinish(RatingCount);
            }
        });
    }


    public void initTips(ArrayList<String> data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data));
    }

    public void initTitle() {
        TitleBean titleBean = new TitleBean("返回", "辩题", "确定");
        bind.test.setTitle2(titleBean);
    }

    public String getRemark() {
        return bind.remark.getText().toString();
    }



}
