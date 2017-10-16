package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.UrlConstant;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.image.ImageFrag;
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

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;

public class UserInfoFrag extends BaseServerFrag<UserInfoUIOpe, UserInfoDAOpe> implements ViewListener {


    @Override
    public void doThing() {
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
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
        setTitleBean(new TitleBean("返回", "信息", ""));
        getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
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
        CommentBean commentBean = getP().getD().getCommentReq(Value.userBean, getP().getD().getUserBean());
        getP().getD().getRemarks(commentBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> a = (ArrayList<CommentBean>) o;
                getP().getD().getCommentBeen().addAll(a);
                getP().getD().setCommentBeen(getP().getD().getCommentBeen());
                getP().getU().initRemarks(getP().getD().getCommentBeen(), UserInfoFrag.this);
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
        getP().getD().getUserBean().setPagestart(getP().getD().getUserBean().getPagestart());
        CommentBean commentBean = getP().getD().getCommentReq(Value.userBean, getP().getD().getUserBean());
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
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.call:
                if (Value.room == null) {
                    ToastUtil.getInstance().showShort(activity, "对方不在线");
                }
                EMClient.getInstance().chatroomManager().asyncFetchChatRoomMembers(Value.room.getId(), null, 100, new EMValueCallBack<EMCursorResult<String>>() {
                    @Override
                    public void onSuccess(EMCursorResult<String> value) {
                        getP().getD().getOtherUsersInfoByPhone(getP().getD().getUserBean(), value.getData(), new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                if ((Boolean) o) {
                                    VideoBean videoBean = new VideoBean();
                                    videoBean.setToUser(getP().getD().getUserBean());
                                    videoBean.setFromUser(Value.userBean);
                                    videoBean.setFromphone(Value.userBean.getPhone());
                                    videoBean.setTophone(getP().getD().getUserBean().getPhone());

                                    VideoChatFrag videoChatFrag = new VideoChatFrag();
                                    videoChatFrag.setArguments(new Bundle());
                                    videoChatFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
                                    FragmentUtil2.getInstance().add(fragment.getActivity(), Value.FULLSCREEN, videoChatFrag);
                                } else {
                                    if (Value.room == null) {
                                        ToastUtil.getInstance().showShort(activity, "对方不在线");
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(int error, String errorMsg) {

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
                imageFrag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentUtil2.getInstance().removeTop(activity, Value.FULLSCREEN);
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
                    case R.id.iv_agree:
                        final CommentBean commentBean = (CommentBean) v.getTag(R.id.data);
                        AgreeBean agreeBean = new AgreeBean();
                        agreeBean.setCommentid(commentBean.getId());
                        agreeBean.setAgreeid(Value.userBean.getId());
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
