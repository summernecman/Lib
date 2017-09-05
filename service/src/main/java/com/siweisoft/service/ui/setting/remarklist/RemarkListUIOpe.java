package com.siweisoft.service.ui.setting.remarklist;

//by summer on 17-08-28.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragRemarklistBinding;
import com.siweisoft.service.netdb.comment.CommentBean;

import java.util.ArrayList;

public class RemarkListUIOpe extends BaseUIOpe<FragRemarklistBinding> {


    public RemarkListUIOpe(Context context) {
        super(context);
    }

    public void initRemarks(ArrayList<CommentBean> data) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data));
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
