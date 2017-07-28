package com.siweisoft.service.ui.record.record;

//by summer on 2017-07-11.

import android.content.Context;
import android.webkit.WebViewClient;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragRecordBinding;

public class RecordUIOpe extends BaseUIOpe<FragRecordBinding> {


    public RecordUIOpe(Context context) {
        super(context);
    }

    public void loadUrl(String url) {
        bind.web.setWebViewClient(new WebViewClient());
        bind.web.getSettings().setJavaScriptEnabled(true);
        bind.web.loadUrl("http://" + url);
    }
}
