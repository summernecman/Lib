package com.summer.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.summer.lib.base.ope.BaseUIOpe;

import java.util.ArrayList;


public class ImagePagerUIOpe extends BaseUIOpe<ImagePagerUIBean> {


    public ImagePagerUIOpe(Context context) {
        super(context, new ImagePagerUIBean(context, null));
    }

    public void initPager(FragmentManager fragmentManager, ArrayList<String> strs, int position) {
        getUiBean().getVpVp().setAdapter(new ImagePagerAdaper(fragmentManager, context, strs));
        getUiBean().getVpVp().setCurrentItem(position);
    }
}
