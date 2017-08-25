package com.summer.desktop.module.note.notemain;

//by summer on 2017-07-28.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.summer.desktop.module.note.bean.NoteOrBookBean;

import java.util.ArrayList;

public class NoteMainFrag extends BaseUIFrag<NoteMainUIOpe, NoteMainDAOpe> {


    @Override
    public void doThing() {
        getP().getD().setNoteOrBooks((ArrayList<NoteOrBookBean>) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().setPosition(getArguments().getInt(ValueConstant.DATA_POSITION));
        getP().getU().initViewPager(getChildFragmentManager(), activity, getP().getD().getFragments(), getP().getD().getPosition());
    }
}
