package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.GsonUtil;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragVideoplayBinding;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoPlayUIOpe extends BaseUIOpe<FragVideoplayBinding> {


    public VideoPlayUIOpe(Context context) {
        super(context);
    }

    public void play(VideoBean videoBean) {
        bind.videoplayer.setUp(videoBean.getFile(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoBean.getCreated());
        //bind.videoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }

    public void initTips(TipsBean data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data.getTipBeen()));
    }

    public void initInfo(CommentBean commentBean) {
        bind.remark.setText(commentBean.getRemark() + "");
        TipsBean tipsBean = GsonUtil.getInstance().fromJson(commentBean.getTips(), TipsBean.class);
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipsBean.getTipBeen()));
    }
}
