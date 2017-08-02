package com.android.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;

import java.util.ArrayList;

public class ImagePagerFrag extends BaseUIFrag<ImagePagerUIOpe, ImagePagerDAOpe> {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(ValueConstant.DATA_DATA) != null) {
            P().D().setUrls((ArrayList<String>) getArguments().getSerializable(ValueConstant.DATA_DATA));
            P().D().setPostion(getArguments().getInt(ValueConstant.DATA_POSITION));
        } else {
            P().D().setUrls(new ArrayList<String>());
        }
        P().U().initPager(getActivity().getSupportFragmentManager(), P().D().getUrls(), P().D().getPostion());
    }
}
