package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.UrlConstant;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.NetWork;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.image.ImageFrag;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.agree.AgreeBean;
import com.siweisoft.service.netdb.agree.AgreeNumBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;

public class UserInfoFrag extends BaseServerFrag<UserInfoUIOpe, UserInfoDAOpe> implements ViewListener {


    @Override
    public void doThing() {
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                init();
                materialRefreshLayout.finishRefreshingDelay();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                initData2();

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        if (((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA)) != null) {
            init();
        } else {
            getP().getD().getUserInfo(getArguments().getInt(Value.DATA_POSITION), new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    if (o != null && o instanceof UserBean) {
                        getP().getD().setUserBean((UserBean) o);
                        init();
                    }
                }
            });
        }


    }

    public void init() {
        if (getP().getD().getUserBean() == null) {
            getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        }
        if (getP().getD().getUserBean() == null) {
            return;
        }
        setTitleBean(new TitleBean("返回", "信息", ""));
        LogUtil.E(GsonUtil.getInstance().toJson(getP().getD().getUserBean()));
        getP().getD().getUserCenterDAOpe().getUserTips(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initTips((HashMap<Integer, TipBean>) o);
            }
        });
        getP().getD().getUserBean().setPagesize(5);
        getP().getD().getUserBean().setPagestart(0);
        getP().getD().getUserBean().setPagestart(getP().getD().getUserBean().getPagestart());
        getP().getD().getCommentBeen().clear();
        CommentBean commentBean = getP().getD().getCommentReq(Value.getUserInfo(), getP().getD().getUserBean());
        getP().getD().getRemarks(commentBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> a = (ArrayList<CommentBean>) o;
                getP().getD().getCommentBeen().addAll(a);
                getP().getD().setCommentBeen(getP().getD().getCommentBeen());
                getP().getU().initRemarks(getP().getD().getCommentBeen(), UserInfoFrag.this, new MyRecyclerView.OnScroll() {
                    @Override
                    public void onScrollToEnd(MyRecyclerView myRecyclerView) {
                        initData2();
                    }
                });
                getP().getD().getUserBean().setPagestart(getP().getD().getUserBean().getPagestart() + 1);
            }
        });

        getP().getD().getUserCallInfo(getP().getD().getUserBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initCallInfo((VideoTimeBean) o);
            }
        });
        getP().getU().initHead(getP().getD().getUserBean());
        getP().getU().initOnline(getP().getD().isOnline(getP().getD().getUserBean().getPhone()));
    }


    public void initData2() {
        if (getP().getD().getUserBean() == null) {
            return;
        }
        getP().getD().getUserBean().setPagestart(getP().getD().getUserBean().getPagestart());
        CommentBean commentBean = getP().getD().getCommentReq(Value.getUserInfo(), getP().getD().getUserBean());
        getP().getD().getRemarks(commentBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().bind.refresh.finishRefreshLoadMore();
                ArrayList<CommentBean> a = (ArrayList<CommentBean>) o;
                if (a == null || a.size() == 0) {
                    ToastUtil.getInstance().showShort(activity, "已经加载完了");
                }
                getP().getD().getCommentBeen().addAll(a);
                getP().getU().refreshRemarks();
                getP().getD().getUserBean().setPagestart(getP().getD().getUserBean().getPagestart() + 1);
            }
        });

    }

    @OnClick({R.id.call, R.id.tv_phone, R.id.iv_head11})
    public void onClickEvent(final View v) {
        if (getP().getD().getUserBean() == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.call:
                if (Value.getRoom() == null) {
                    ToastUtil.getInstance().showShort(activity, "对方不在线");
                    return;
                }
                v.setEnabled(false);
                EMClient.getInstance().chatroomManager().asyncFetchChatRoomMembers(Value.getRoom().getId(), null, 100, new EMValueCallBack<EMCursorResult<String>>() {
                    @Override
                    public void onSuccess(EMCursorResult<String> value) {
                        getP().getD().getOtherUsersInfoByPhone(getP().getD().getUserBean(), value.getData(), new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                if ((Boolean) o) {
                                    VideoBean videoBean = new VideoBean();
                                    videoBean.setToUser(getP().getD().getUserBean());
                                    videoBean.setFromUser(Value.getUserInfo());
                                    videoBean.setFromphone(Value.getUserInfo().getPhone());
                                    videoBean.setTophone(getP().getD().getUserBean().getPhone());

                                    VideoChatFrag videoChatFrag = new VideoChatFrag();
                                    videoChatFrag.setArguments(new Bundle());
                                    videoChatFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
                                    FragmentUtil2.getInstance().add(fragment.getActivity(), Value.FULLSCREEN, videoChatFrag);
                                } else {
                                    if (Value.getRoom() == null) {
                                        ToastUtil.getInstance().showShort(activity, "对方不在线");
                                    }
                                }
                                v.setEnabled(true);
                            }
                        });
                    }

                    @Override
                    public void onError(int error, String errorMsg) {
                        v.setEnabled(true);
                    }
                });
                break;
            case R.id.tv_phone:
                //PhoneUtil.getInstance().Call(activity,getP().getD().getUserBean().getPhone());
                break;
            case R.id.iv_head11:
                ImageFrag imageFrag = new ImageFrag();
                imageFrag.setArguments(new Bundle());
                imageFrag.getArguments().putString(Value.DATA_DATA, UrlConstant.fileUrl + "/" + getP().getD().getUserBean().getHeadurl());
                imageFrag.getArguments().putBoolean(ValueConstant.DATA_POSITION, getP().getD().isFileExit(getP().getD().getUserBean().getHeadurl()));
                imageFrag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        switch (v.getId()) {
                            case R.id.tv_back:
                                FragmentUtil2.getInstance().removeTop(activity, Value.FULLSCREEN);
                                break;
                            case R.id.iv_download:
                                if (v.isSelected()) {
                                    return;
                                }
                                v.setEnabled(false);
                                getP().getD().downloadFile(UrlConstant.fileUrl + "/" + getP().getD().getUserBean().getHeadurl(), new NetWork.MyFileDownloadCallBack<File>() {
                                    @Override
                                    public void onLoading(long total, long current, boolean isDownloading) {
                                        super.onLoading(total, current, isDownloading);
                                    }

                                    @Override
                                    public void onSuccess(final File result) {
                                        super.onSuccess(result);
                                        v.setEnabled(true);
                                        v.setSelected(true);
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
                                        ToastUtil.getInstance().showShort(activity, "error");
                                    }
                                });
                                break;
                        }
                    }
                });
                FragmentUtil2.getInstance().add(activity, Value.FULLSCREEN, imageFrag);
                break;
        }
    }

    @Override
    public void onInterupt(int type, final View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()) {
                    case R.id.iv_head:
                        final CommentBean commentBean1 = (CommentBean) v.getTag(R.id.data);
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        userInfoFrag.getArguments().putInt(ValueConstant.DATA_POSITION, commentBean1.getFromid());
                        FragmentUtil2.getInstance().add(activity, Value.getNowRoot(), userInfoFrag);
                        break;
                    case R.id.iv_agree:
                        final CommentBean commentBean = (CommentBean) v.getTag(R.id.data);
                        AgreeBean agreeBean = new AgreeBean();
                        agreeBean.setCommentid(commentBean.getId());
                        agreeBean.setAgreeid(Value.getUserInfo().getId());
                        getP().getD().clickAgree(agreeBean, new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                AgreeNumBean res = (AgreeNumBean) o;
                                v.setSelected(res.isAgree());
                                TextView textView = (TextView) v.getTag(R.id.data1);
                                textView.setText(StringUtil.getStr(res.getAgreenum()));
                                commentBean.setAgree(res.isAgree());
                                commentBean.setAgreeNum(res.getAgreenum());
                            }
                        });
                        break;
                }
                break;
        }
    }
}
