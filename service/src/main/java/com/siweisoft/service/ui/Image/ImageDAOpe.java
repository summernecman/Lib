package com.siweisoft.service.ui.Image;

//by summer on 17-09-08.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

public class ImageDAOpe extends BaseDAOpe {

    private String url;

    public ImageDAOpe(Context context) {
        super(context);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
