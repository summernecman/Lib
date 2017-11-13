package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.collection.CollectionBean;
import com.siweisoft.service.netdb.share.ShareBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userlist.UserListFrag;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

public class VideoContainerFrag extends BaseServerFrag<VideoContainerUIOpe, VideoContainerDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "视频播放", ""));
        getP().getD().setType(getArguments().getInt(ValueConstant.DATA_TYPE, 0));
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initViewPager(VideoContainerFrag.this, getP().getD().getVideosPager(getP().getD().getVideoBean(), getP().getD().getType()));
        getP().getU().initClick(this);


        switch (getP().getD().getType()) {
            case VideoPlayFrag.TYPE_FROM_RECORD:
                getP().getD().isCollectedByVideoIdAndUserId(getP().getD().getVideoBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if (o == null) {
                            getP().getU().setCollect(false);
                        } else {
                            getP().getU().setCollect(true);
                            CollectionBean collectionBean = (CollectionBean) o;
                            getP().getD().getCollectionBean().setId(collectionBean.getId());
                        }
                    }
                });
                break;
            case VideoPlayFrag.TYPE_FROM_COLLECT:
                break;
            case VideoPlayFrag.TYPE_FROM_SHARE:
                getP().getD().isCollectedByVideoIdAndUserId(getP().getD().getVideoBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if (o == null) {
                            getP().getU().setCollect(false);
                        } else {
                            getP().getU().setCollect(true);
                            CollectionBean collectionBean = (CollectionBean) o;
                            getP().getD().getCollectionBean().setId(collectionBean.getId());
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                getP().getU().switchFragment();
                break;
            case R.id.iv_collect:
                if (getP().getU().bind.ivCollect.isSelected()) {
                    getP().getD().disCollect(getP().getD().getCollectionBean(), new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {
                            if ((Boolean) o) {
                                ToastUtil.getInstance().showShort(activity, "取消收藏");
                                getP().getU().setCollect(false);
                            }
                        }
                    });
                } else {
                    getP().getD().collect(getP().getD().getCollectionBean(), new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {
                            if (o instanceof CollectionBean) {
                                ToastUtil.getInstance().showShort(activity, "收藏成功");
                                getP().getU().setCollect(true);
                                getP().getD().getCollectionBean().setId(((CollectionBean) o).getId());
                            }
                        }
                    });
                }
                break;
            case R.id.iv_share:
                final UserBean userBean = new UserBean();
                UserListFrag userListFrag = new UserListFrag();
                userListFrag.setArguments(new Bundle());
                //userListFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getUserBean());
                FragmentUtil2.getInstance().add(activity, Value.getNowRoot(), userListFrag);
                userListFrag.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if (o == null) {
                            return;
                        }
                        UserBean userBean1 = (UserBean) o;
                        ShareBean shareBean = new ShareBean();
                        shareBean.setSendid(Value.getUserInfo().getId());
                        shareBean.setReceiptid(userBean1.getId());
                        shareBean.setVideoid(getP().getD().getVideoBean().getId());
                        getP().getD().share(shareBean, new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                ToastUtil.getInstance().showShort(activity, "分享成功");
                            }
                        });
                    }
                });
                break;
        }
    }
}
