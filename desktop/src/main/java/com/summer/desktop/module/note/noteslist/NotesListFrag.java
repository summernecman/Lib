package com.summer.desktop.module.note.noteslist;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.summer.desktop.module.note.main.NoteMainFrag;
import com.summer.desktop.util.TitleUtil;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class NotesListFrag extends BaseUIFrag<NotesListUIOpe, NotesListDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().initpager(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TitleUtil.getInstance().getName().remove(TitleUtil.getInstance().getName().get(TitleUtil.getInstance().getName().size() - 1));
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.dealer = NoteMainFrag.class.getName();
        EventBus.getDefault().post(messageEvent);
        EventBus.getDefault().post(messageEvent);
    }
}
