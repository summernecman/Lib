package com.siweisoft.service.ui.Image;

//by summer on 17-09-08.

import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.base.BaseServerFrag;

public class ImageFrag extends BaseServerFrag<ImageUIOpe, ImageDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setUrl(getArguments().getString(ValueConstant.DATA_DATA));
        getP().getU().initPic(getP().getD().getUrl());
    }
}
