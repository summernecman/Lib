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
import com.android.lib.util.NullUtil;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragVideoplayBinding;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatDAOpe;

import java.io.File;


public class VideoPlayUIOpe extends BaseUIOpe<FragVideoplayBinding> {

    OrientationUtils orientationUtils;

    public VideoPlayUIOpe(Context context) {
        super(context);
    }

    public void initUpload(VideoBean videoBean, View.OnClickListener onClickListener) {


//        if (Value.getUserInfo().getUsertype() == UserBean.USER_TYPE_CUSTOMER) {
//            bind.tvUpload.setVisibility(View.GONE);
//            return;
//        }
        if (videoBean.getUploaded() == 0) {
            bind.tvUpload.setVisibility(View.VISIBLE);
            if (VideoChatDAOpe.isRecordVideo(videoBean)) {
                bind.tvUpload.setText("本地未上传");
                bind.tvUpload.setOnClickListener(onClickListener);
            } else {
                bind.tvUpload.setText("对方未上传");
            }
        } else {
            bind.tvUpload.setVisibility(View.GONE);
        }
    }

    public void initDownload(VideoBean videoBean, View.OnClickListener onClickListener) {
        bind.ivDownload.setOnClickListener(onClickListener);
        if (videoBean.getUploaded() == 0) {
            bind.ivDownload.setVisibility(View.GONE);
        } else {
            bind.ivDownload.setVisibility(View.VISIBLE);
            String[] ss = videoBean.getFile().split("/");
            if (ss.length > 0) {
                String name = ss[ss.length - 1];
                File file = new File(Value.getCacheFile(), name);
                if (file.exists()) {
                    bind.ivDownload.setSelected(true);
                } else {
                    bind.ivDownload.setSelected(false);
                }
            }
        }

    }

    public void play(VideoBean videoBean, String local, final OnFinishListener onFinishListener) {
        File file = new File(local);
        if (NullUtil.isStrEmpty(local) || !file.exists()) {
            bind.videoplayer.setUp(videoBean.getFile(), true, Value.getCacheFile(), videoBean.getCreated());
        } else {
            bind.videoplayer.setUp(file.getPath(), true, Value.getCacheFile(), videoBean.getCreated());
        }
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils((Activity) context, bind.videoplayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        bind.videoplayer.setIsTouchWiget(true);
        bind.videoplayer.setIsTouchWigetFull(false);
        bind.videoplayer.getBackButton().setVisibility(View.GONE);
        bind.videoplayer.getTitleTextView().setVisibility(View.GONE);


        //关闭自动旋转
        bind.videoplayer.setRotateViewAuto(true);
        bind.videoplayer.setLockLand(false);
        bind.videoplayer.setShowFullAnimation(false);
        bind.videoplayer.setNeedLockFull(true);
        bind.videoplayer.setSeekRatio(1);
        bind.videoplayer.setRotateWithSystem(true);
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

    public void initRating(float rate) {
        bind.ratingbar.setStar(rate);
    }

    public void initTips(TipsBean data) {

        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip5, BR.item_tip5, data.getTipBeen()) {

        });
    }

    public void initInfo(CommentBean commentBean) {
        bind.ratingbar.setStar(commentBean.getRate());
        bind.remark.setText(commentBean.getRemark() + "");
        TipsBean tipsBean = GsonUtil.getInstance().fromJson(commentBean.getTips(), TipsBean.class);
        for (int i = 0; tipsBean != null && tipsBean.getTipBeen() != null && i < tipsBean.getTipBeen().size(); i++) {
            if (tipsBean.getTipBeen().get(i).getNum() == 0) {
                tipsBean.getTipBeen().remove(i);
                i--;
            }
        }
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip5, BR.item_tip5, tipsBean.getTipBeen()));
    }

    public void initTxt(VideoBean videoBean, boolean show) {
        // bind.remark.setText(show ? videoBean.getCreated() + "" : "");
    }

    public OrientationUtils getOrientationUtils() {
        return orientationUtils;
    }


//    public void initShare(int vis, String txt, View.OnClickListener onClickListener) {
//        MainAct act = (MainAct) context;
//        act.getP().getU().bind.tophead.ftvRight2.setVisibility(vis);
//        act.getP().getU().bind.tophead.ftvRight2.setText(txt);
//        act.getP().getU().bind.tophead.ftvRight2.setOnClickListener(onClickListener);
//    }
}
