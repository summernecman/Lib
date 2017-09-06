package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.ToastUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;


public class VideoPlayFrag extends BaseServerFrag<VideoPlayUIOpe, VideoPlayDAOpe> {

    @Override
    public void doThing() {
        setTitleBean(new TitleBean("返回", "视频播放", "收藏"));
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initTips(getP().getD().userInfoDAOpe.getData());
        getP().getU().play(getP().getD().getVideoBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initTxt(getP().getD().getVideoBean(), (boolean) o);
            }
        });
        getP().getD().getComment(getP().getD().getVideoBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> commentBeen = (ArrayList<CommentBean>) o;
                if (commentBeen != null && commentBeen.size() > 0) {
                    getP().getU().initInfo(commentBeen.get(0));
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.ftv_right).setOnClickListener(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        getP().getU().getOrientationUtils().releaseListener();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right:
                getP().getD().collect(getP().getD().getVideoBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if ((Boolean) o) {
                            ToastUtil.getInstance().showShort(activity, "收藏成功");
                        }
                    }
                });
                break;
        }
    }
}
