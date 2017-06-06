package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class WebViewItemBean extends BaseUIBean {


    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.textView)
    TextView textView;

    public WebViewItemBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_webview);
    }

    public WebView getWebview() {
        return webview;
    }

    public TextView getTextView() {
        return textView;
    }
}
