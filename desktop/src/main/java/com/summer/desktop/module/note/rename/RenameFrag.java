package com.summer.desktop.module.note.rename;

//by summer on 2017-06-06.

import android.view.View;

import com.summer.desktop.R;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.util.FragmentUtil;

import butterknife.OnClick;

public class RenameFrag extends BaseUIFrag<ReNameUIOpe, RenameDAOpe> {

    OnFinishListener onfinish;



    @OnClick({R.id.delete})
    public void onClick(View v) {
        FragmentUtil.getInstance().removeTop(getActivity());
        if (onfinish != null) {
            onfinish.onFinish(getOpes().getUiOpe().getUiBean().getTxt().getText().toString());
        }
    }

    public void setOnfinish(OnFinishListener onfinish) {
        this.onfinish = onfinish;
    }
}
