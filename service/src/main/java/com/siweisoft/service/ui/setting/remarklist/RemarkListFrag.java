package com.siweisoft.service.ui.setting.remarklist;

//by summer on 17-08-28.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;

import java.util.ArrayList;

public class RemarkListFrag extends BaseServerFrag<RemarkListUIOpe, RemarkListDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        super.doThing();
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
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
        setTitleBean(new TitleBean("返回", "评论列表", ""));
        getP().getD().setPageindex(0);
        Value.getUserInfo().setPagesize(getP().getD().getPagesize());
        Value.getUserInfo().setPagestart(getP().getD().getPageindex());
        getP().getD().getRemarks(Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().setList((ArrayList<CommentBean>) o);
                getP().getU().initRemarks(getP().getD().getList(), new MyRecyclerView.OnScroll() {
                    @Override
                    public void onScrollToEnd(MyRecyclerView myRecyclerView) {
                        initData2();
                    }
                }, RemarkListFrag.this);
                getP().getD().setPageindex(getP().getD().getPageindex() + 1);
                getP().getU().bind.refresh.finishRefreshingDelay();
            }
        });
    }

    public void initData2() {
        setTitleBean(new TitleBean("返回", "评论列表", ""));
        Value.getUserInfo().setPagesize(getP().getD().getPagesize());
        Value.getUserInfo().setPagestart(getP().getD().getPageindex());
        getP().getD().getRemarks(Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> list = (ArrayList<CommentBean>) o;
                if (list == null || list.size() == 0) {
                    ToastUtil.getInstance().showShort(activity, "已经加载完了");
                    getP().getU().bind.refresh.finishRefreshLoadMore();
                    return;
                }
                if (list != null) {
                    getP().getD().getList().addAll(list);
                }
                getP().getU().refreshRemarks(getP().getD().getList());
                getP().getD().setPageindex(getP().getD().getPageindex() + 1);
                getP().getU().bind.refresh.finishRefreshLoadMore();
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
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
                }
                break;
        }
    }
}
