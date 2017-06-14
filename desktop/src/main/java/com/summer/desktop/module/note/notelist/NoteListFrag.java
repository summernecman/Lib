package com.summer.desktop.module.note.notelist;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.google.gson.Gson;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.dabean.TitleDABean;
import com.summer.desktop.module.note.circlemenu.CircleMenuFrag;
import com.summer.desktop.module.note.noteslist.NotesListFrag;
import com.summer.desktop.module.note.rename.RenameFrag;
import com.summer.desktop.util.FragList;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.view.refreshlayout.MaterialRefreshLayout;
import com.summer.lib.view.refreshlayout.MaterialRefreshListenerAdpter;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

public class NoteListFrag extends BaseUIFrag<NoteListUIOpe, NoteListDAOpe> {

    Random random = new Random();
    Gson gson = new Gson();

    @Override
    public BaseOpes<NoteListUIOpe, NoteListDAOpe> createOpes() {
        return new BaseOpes<>(new NoteListUIOpe(activity), new NoteListDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUiOpe().init(fragment, this, this, new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                materialRefreshLayout.finishRefresh();
            }
        });
        getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
    }

    @Override
    public void onClick(View v) {
        NotesListFrag noteListssFrag = new NotesListFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", getOpes().getUiOpe().notes);
        bundle.putInt("position", (Integer) v.getTag(R.id.position));
        noteListssFrag.setArguments(bundle);
        FragList.getInstance().add(getActivity(), noteListssFrag);

    }

    @Override
    public boolean onLongClick(final View v) {
        CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.homeroot, circleMenuFrag);
        transaction.commit();
        final Note note1 = (Note) v.getTag(R.id.data);
        circleMenuFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                int index = (int) o;
                switch (index) {
                    case 0:
                        //note
                        getOpes().getDaOpe().createNote(getOpes().getUiOpe().parentNote.getObjectId(), new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                            }
                        });
                        break;
                    case 1:
                        //notebook
                        getOpes().getDaOpe().createNoteBook(getOpes().getUiOpe().parentNote.getObjectId(), new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                            }
                        });
                        break;
                    case 2:
                        //delete
                        getOpes().getDaOpe().deleteNote(note1, new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                            }
                        });
                        break;
                    case 3:
                        //rename
                        if (v.getTag(R.id.data) == null) {
                            break;
                        }
                        RenameFrag renameFrag = new RenameFrag();
                        FragList.getInstance().add(getActivity(), renameFrag);
                        renameFrag.setOnfinish(new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                getOpes().getDaOpe().renameNote((String) o, note1, new OnFinishListener() {
                                    @Override
                                    public void onFinish(Object o) {
                                        getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                                    }
                                });
                            }
                        });
                        break;
                }
            }
        });
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new TitleDABean(""));
    }
}
