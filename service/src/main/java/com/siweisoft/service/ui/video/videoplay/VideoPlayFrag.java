package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.netdb.video.VideoBean;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class VideoPlayFrag extends BaseUIFrag<VideoPlayUIOpe, VideoPlayDAOpe> {

    @Override
    public void doThing() {
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initTips(getP().getD().userInfoDAOpe.getData());
        getP().getU().play(getP().getD().getVideoBean());
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
