package com.summer.desktop.module.rename;

//by summer on 2017-06-06.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil;
import com.summer.desktop.R;

import butterknife.OnClick;

public class RenameFrag extends BaseUIFrag<ReNameUIOpe, RenameDAOpe> {

    OnFinishListener onfinish;

    @OnClick({R.id.delete})
    public void onClick(View v) {
        FragmentUtil.getInstance().removeTop(getActivity());
        if (onfinish != null) {
            onfinish.onFinish(getP().getU().bind.txt.getText().toString());
        }
    }

    public void setOnfinish(OnFinishListener onfinish) {
        this.onfinish = onfinish;
    }
}
