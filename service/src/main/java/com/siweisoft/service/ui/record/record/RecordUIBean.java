package com.siweisoft.service.ui.record.record;

//by summer on 2017-07-11.

import android.content.Context;
import android.webkit.WebViewClient;

import com.android.lib.base.ope.BaseUIBean;
import com.siweisoft.service.databinding.FragRecordBinding;

public class RecordUIBean extends BaseUIBean<FragRecordBinding> {


    public RecordUIBean(Context context) {
        super(context);
    }

    public void loadUrl(String url) {
        viewDataBinding.web.setWebViewClient(new WebViewClient());
        viewDataBinding.web.getSettings().setJavaScriptEnabled(true);
        viewDataBinding.web.loadUrl("http://" + url);
    }
}
