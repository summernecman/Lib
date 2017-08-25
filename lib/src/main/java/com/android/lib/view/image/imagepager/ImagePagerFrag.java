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
            getP().getD().setUrls((ArrayList<String>) getArguments().getSerializable(ValueConstant.DATA_DATA));
            getP().getD().setPostion(getArguments().getInt(ValueConstant.DATA_POSITION));
        } else {
            getP().getD().setUrls(new ArrayList<String>());
        }
        getP().getU().initPager(getActivity().getSupportFragmentManager(), getP().getD().getUrls(), getP().getD().getPostion());
    }
}
