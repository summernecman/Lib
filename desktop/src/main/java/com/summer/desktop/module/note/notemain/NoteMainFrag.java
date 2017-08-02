package com.summer.desktop.module.note.notemain;

//by summer on 2017-07-28.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.summer.desktop.module.note.bean.NoteOrBookBean;

public class NoteMainFrag extends BaseUIFrag<NoteMainUIOpe, NoteMainDAOpe> {


    @Override
    public void doThing() {
        P().D().setNoteOrBookBean((NoteOrBookBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        P().U().initViewPager(getChildFragmentManager(), activity, P().D().getFragments());
    }
}
