package com.summer.desktop.module.note.rename;

//by summer on 2017-06-06.

import android.view.View;

import com.summer.desktop.R;
import com.summer.desktop.util.FragList;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseOpes;

import butterknife.OnClick;

public class RenameFrag extends BaseUIFrag<ReNameUIOpe, RenameDAOpe> {

    OnFinishListener onfinish;


    @Override
    public BaseOpes<ReNameUIOpe, RenameDAOpe> createOpes() {
        return new BaseOpes<>(new ReNameUIOpe(activity), new RenameDAOpe(activity));
    }

    @OnClick({R.id.delete})
    public void onClick(View v) {
        FragList.getInstance().removeTop(getActivity());
        if (onfinish != null) {
            onfinish.onFinish(getOpes().getUiOpe().getUiBean().getTxt().getText().toString());
        }
    }

    public void setOnfinish(OnFinishListener onfinish) {
        this.onfinish = onfinish;
    }
}
