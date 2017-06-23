package com.android.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class ImagePagerDAOpe extends BaseDAOpe {

    ArrayList<String> urls;

    private int postion;


    public ImagePagerDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
