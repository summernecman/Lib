package com.summer.time.ui.main.note;

//by summer on 2017-11-24.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.time.R;

import butterknife.OnLongClick;

public class NoteFrag extends BaseUIFrag<NoteUIOpe, NoteDAOpe> {

    @OnLongClick({R.id.iv_edit})
    public boolean onClickEvent(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_edit:
                getP().getU().switchEnable();
                break;
        }
        return true;
    }
}
