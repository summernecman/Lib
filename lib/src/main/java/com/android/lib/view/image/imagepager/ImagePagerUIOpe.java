package com.android.lib.view.image.imagepager;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.databinding.ActivityImagesVpBinding;

import java.util.ArrayList;

public class ImagePagerUIOpe extends BaseUIOpe<ActivityImagesVpBinding> {


    public ImagePagerUIOpe(Context context) {
        super(context);
    }

    public void initPager(FragmentManager fragmentManager, ArrayList<String> strs, int position) {
        bind.vpVp.setAdapter(new ImagePagerAdaper(fragmentManager, context, strs));
        bind.vpVp.setCurrentItem(position);

    }

}