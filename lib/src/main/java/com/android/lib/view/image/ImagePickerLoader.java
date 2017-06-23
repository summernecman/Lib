package com.android.lib.view.image;

//by summer on 2017-06-12.

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

public class ImagePickerLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(path).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
