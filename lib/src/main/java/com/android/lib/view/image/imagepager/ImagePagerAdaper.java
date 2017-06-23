package com.android.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.constant.ValueConstant;
import com.android.lib.view.image.ImageFrag;

import java.util.ArrayList;

public class ImagePagerAdaper extends AppBasePagerAdapter {

    ArrayList<String> data;

    public ImagePagerAdaper(FragmentManager fm, Context context, ArrayList<String> data) {
        super(fm, context);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFrag imageFrag = new ImageFrag();
        Bundle bundle = new Bundle();
        bundle.putString(ValueConstant.DATA_DATA, data.get(position));
        imageFrag.setArguments(bundle);
        return imageFrag;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }
}
