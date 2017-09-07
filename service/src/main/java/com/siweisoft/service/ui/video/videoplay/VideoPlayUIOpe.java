package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.GsonUtil;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragVideoplayBinding;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;


public class VideoPlayUIOpe extends BaseUIOpe<FragVideoplayBinding> {

    OrientationUtils orientationUtils;

    public VideoPlayUIOpe(Context context) {
        super(context);
    }

    public void play(VideoBean videoBean, final OnFinishListener onFinishListener) {
        bind.videoplayer.setUp(videoBean.getFile(), true, Value.getCacheFile(), videoBean.getCreated());
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils((Activity) context, bind.videoplayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        bind.videoplayer.setIsTouchWiget(true);
        bind.videoplayer.setIsTouchWigetFull(false);
        bind.videoplayer.getBackButton().setVisibility(View.GONE);
        bind.videoplayer.getTitleTextView().setVisibility(View.GONE);


        //关闭自动旋转
        bind.videoplayer.setRotateViewAuto(false);
        bind.videoplayer.setLockLand(false);
        bind.videoplayer.setShowFullAnimation(false);
        bind.videoplayer.setNeedLockFull(true);
        bind.videoplayer.setSeekRatio(1);
        //detailPlayer.setOpenPreView(false);
        bind.videoplayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                //orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                bind.videoplayer.startWindowFullscreen(context, true, true);
                onFinishListener.onFinish(false);
            }
        });

        bind.videoplayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
                ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
                onFinishListener.onFinish(true);
            }
        });

        bind.videoplayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
        //bind.videoplayer.setUp(videoBean.getFile(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoBean.getCreated());
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

    public void initTxt(VideoBean videoBean, boolean show) {
        bind.remark.setText(show ? videoBean.getCreated() + "" : "");
    }

    public OrientationUtils getOrientationUtils() {
        return orientationUtils;
    }


    public void initShare(int vis, String txt, View.OnClickListener onClickListener) {
        MainAct act = (MainAct) context;
        act.getP().getU().bind.tophead.ftvRight2.setVisibility(vis);
        act.getP().getU().bind.tophead.ftvRight2.setText(txt);
        act.getP().getU().bind.tophead.ftvRight2.setOnClickListener(onClickListener);
    }
}
