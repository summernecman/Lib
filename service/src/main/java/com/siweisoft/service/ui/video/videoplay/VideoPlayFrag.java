package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.videodetail.VideoDetailBean;

import java.io.File;
import java.util.ArrayList;


public class VideoPlayFrag extends BaseServerFrag<VideoPlayUIOpe, VideoPlayDAOpe> {


    public static final int TYPE_FROM_RECORD = 0;

    public static final int TYPE_FROM_COLLECT = 1;

    public static final int TYPE_FROM_SHARE = 2;


    @Override
    public void doThing() {
        setTitleBean(new TitleBean("返回", "视频播放", ""));
        getP().getD().setType(getArguments().getInt(ValueConstant.DATA_TYPE, 0));
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().setVideoDetailBean((VideoDetailBean) getArguments().getSerializable(ValueConstant.DATA_DATA2));
        getP().getD().getVideoBean().setCommentid(getP().getD().getVideoDetailBean().getUserid());
        getP().getD().getVideoBean().setFile(getP().getD().getVideoDetailBean().getUrl());

        getP().getU().initVideoDetail(getP().getD().getVideoComment(getP().getD().getVideoDetailBean(), getP().getD().getVideoBean()));
        getP().getD().getCollectionBean().setVideoid(getP().getD().getVideoBean().getId());
        getP().getU().initUpload(getP().getD().getVideoDetailBean(), this);
        getP().getU().initDownload(getP().getD().getVideoDetailBean(), this);
        getP().getU().play(getP().getD().getVideoDetailBean(), getP().getD().getLoadFile(getP().getD().getVideoDetailBean()), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {

            }
        });

        getP().getD().isCommentToCustomer(getP().getD().getVideoDetailBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if (!((Boolean) o)) {
                    getP().getU().initTips(getP().getD().userInfoDAOpe.getData());
                    getP().getD().getComment(getP().getD().getVideoBean(), new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {
                            ArrayList<CommentBean> commentBeen = (ArrayList<CommentBean>) o;
                            if (commentBeen != null && commentBeen.size() > 0) {
                                getP().getU().initInfo(commentBeen.get(0));
                            }
                        }
                    });
                } else {
                    getP().getU().hildComment();
                }
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.ftv_right2).setOnClickListener(this);
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
        //getP().getU().getOrientationUtils().releaseListener();
    }


    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_download:
                if (!v.isSelected()) {
                    v.setEnabled(false);

                    getP().getD().downloadFile(getP().getD().getVideoDetailBean(), new NetWork.MyFileDownloadCallBack<File>() {
                        @Override
                        public void onLoading(long total, long current, boolean isDownloading) {
                            super.onLoading(total, current, isDownloading);
                            getP().getU().bind.tvProgress.setText((current * 100 / total) + "%");
                        }

                        @Override
                        public void onSuccess(final File result) {
                            super.onSuccess(result);
                            v.setEnabled(true);
                            v.setSelected(true);
                            getP().getU().bind.tvProgress.setText("");
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.getInstance().showShort(activity, result.getPath());
                                }
                            });
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            super.onError(ex, isOnCallback);
                            v.setEnabled(true);
                            getP().getU().bind.tvProgress.setText("error");
                        }
                    });
                }
                break;
            case R.id.tv_upload:
                v.setEnabled(false);
                getP().getD().uploadVideo(getP().getD().getVideoBean(), getP().getD().getVideoDetailBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if (o instanceof BaseResBean) {
                            BaseResBean baseResBean = (BaseResBean) o;
                            if (baseResBean.isException()) {
                                ToastUtil.getInstance().showShort(activity, "" + baseResBean.getErrorMessage());
                            } else {
                                v.setVisibility(View.GONE);
                                ToastUtil.getInstance().showShort(activity, "上传成功");
                                v.setEnabled(false);
                                getP().getD().getVideoDetailBean().setUploaded(1);
                            }
                        }
                    }
                }, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        getP().getU().bind.tvUpload.setText(StringUtil.getStr(o) + "%");
                    }
                });
                break;
        }
    }
}
