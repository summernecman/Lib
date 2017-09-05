package com.siweisoft.service.ui.setting.remarklist;

//by summer on 17-08-28.

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class RemarkListFrag extends BaseServerFrag<RemarkListUIOpe, RemarkListDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
                materialRefreshLayout.finishRefreshingDelay();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        setTitleBean(new TitleBean("返回", "评论列表", ""));
        getP().getD().getRemarks(Value.userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initRemarks((ArrayList<CommentBean>) o);
            }
        });
    }
}
