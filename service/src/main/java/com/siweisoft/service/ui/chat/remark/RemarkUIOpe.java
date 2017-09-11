package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.StringUtil;
import com.hedgehog.ratingbar.RatingBar;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragRemarkBinding;
import com.siweisoft.service.netdb.user.UserBean;

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


    public void initTips(final TipsBean data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data.getTipBeen()) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                ViewDataBinding viewDataBinding = holder.viewDataBinding;
                viewDataBinding.getRoot().setTag(com.android.lib.R.id.data, list.get(position));
                viewDataBinding.getRoot().setTag(com.android.lib.R.id.position, position);
                viewDataBinding.getRoot().setOnClickListener(this);
                viewDataBinding.getRoot().setOnLongClickListener(this);
                viewDataBinding.setVariable(vari, list.get(position));
                viewDataBinding.getRoot().setSelected(data.getTipBeen().get(position).isSelect());
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                data.getTipBeen().get((Integer) v.getTag(R.id.position)).setSelect(!data.getTipBeen().get((Integer) v.getTag(R.id.position)).isSelect());
                if (data.getTipBeen().get((Integer) v.getTag(R.id.position)).isSelect()) {
                    data.getTipBeen().get((Integer) v.getTag(R.id.position)).setNum(1);
                } else {
                    data.getTipBeen().get((Integer) v.getTag(R.id.position)).setNum(0);
                }
                v.setSelected(data.getTipBeen().get((Integer) v.getTag(R.id.position)).isSelect());
//                notifyDataSetChanged();
            }
        });
    }


    public String getRemark() {
        return bind.remark.getText().toString();
    }


    public void initTop(UserBean userBean) {
        GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/" + userBean.getHeadurl()).into(bind.imageView2);
        bind.tvName.setText(StringUtil.getStr(userBean.getName()));
    }


}
