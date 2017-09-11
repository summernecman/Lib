package com.siweisoft.service.ui.Image;

//by summer on 17-09-08.

import android.app.Activity;
import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.databinding.ActivityImageBinding;
import com.lzy.imagepicker.ImagePicker;

public class ImageUIOpe extends BaseUIOpe<ActivityImageBinding> {


    public ImageUIOpe(Context context) {
        super(context);
    }

    public void initPic(String url) {
        ImagePicker.getInstance().getImageLoader().displayImage((Activity) context, url, bind.ivImage, 0, 0);
    }
}
