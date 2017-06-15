package com.summer.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.summer.lib.R;
import com.summer.lib.bean.uibean.BaseUIBean;

public class ImagePagerUIBean extends BaseUIBean {


    ViewPager vpVp;

    public ImagePagerUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.activity_images_vp);
        vpVp = (ViewPager) itemView.findViewById(R.id.vp_vp);
    }

    public ViewPager getVpVp() {
        return vpVp;
    }
}
