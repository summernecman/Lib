package com.siweisoft.service.ui.record.record;

//by summer on 2017-07-11.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.siweisoft.service.ui.Constant.VideoValue;

public class RecordFrag extends BaseUIFrag<RecordUIOpe, RecordDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().loadUrl(VideoValue.URL.URLROCORD);
    }
}
