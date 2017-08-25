package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideoplayBinding;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoPlayUIOpe extends BaseUIOpe<FragVideoplayBinding> {


    public VideoPlayUIOpe(Context context) {
        super(context);
    }

    public void play(VideoBean videoBean) {
        bind.videoplayer.setUp(videoBean.getFile(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoBean.getCreated());
        //bind.videoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }

    public void initTips(ArrayList<String> data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data));
    }
}
