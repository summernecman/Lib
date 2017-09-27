package com.siweisoft.service.ui.setting.feedback;

//by summer on 17-08-28.

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.ScreenUtil;
import com.hedgehog.ratingbar.RatingBar;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragFeedbackBinding;
import com.siweisoft.service.databinding.ItemPicBinding;

import java.util.ArrayList;

public class FeedBAckUIOpe extends BaseUIOpe<FragFeedbackBinding> {
    public FeedBAckUIOpe(Context context) {
        super(context);
    }

    public void initPics(final ArrayList<Object> data, ViewListener viewListener) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_pic, BR.item_pic, data, viewListener) {

            @Override
            public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                AppViewHolder appViewHolder = new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false));
                ItemPicBinding itemPicBinding = (ItemPicBinding) appViewHolder.viewDataBinding;
                itemPicBinding.tvPic.setLayoutParams(new RelativeLayout.LayoutParams((int) (ScreenUtil.w / 4), (int) (ScreenUtil.w / 4)));
                return appViewHolder;
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemPicBinding picBinding = (ItemPicBinding) holder.viewDataBinding;
                if (data.get(position) instanceof Integer) {
                    picBinding.tvPic.setImageResource((Integer) data.get(position));
                } else {
                    GlideApp.with(context).load(data.get(position)).into(picBinding.tvPic);
                }
            }
        });
    }

    public void initRating(RatingBar.OnRatingChangeListener onRatingChangeListener) {
        bind.ratingbar.setOnRatingChangeListener(onRatingChangeListener);
    }
}
