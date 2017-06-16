package com.summer.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.constant.ValueConstant;

import java.util.ArrayList;

public class ImagePagerFrag extends BaseUIFrag<ImagePagerUIOpe, ImagePagerDAOpe> {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(ValueConstant.DATA_DATA) != null) {
            getOpes().getDaOpe().setUrls((ArrayList<String>) getArguments().getSerializable(ValueConstant.DATA_DATA));
            getOpes().getDaOpe().setPostion(getArguments().getInt(ValueConstant.DATA_POSITION));
        } else {
            getOpes().getDaOpe().setUrls(new ArrayList<String>());
        }
        getOpes().getUiOpe().initPager(getActivity().getSupportFragmentManager(), getOpes().getDaOpe().getUrls(), getOpes().getDaOpe().getPostion());
    }
}
