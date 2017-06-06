package com.summer.desktop.module.notelist;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.google.gson.Gson;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.module.boom.BoomFrag;
import com.summer.desktop.module.noteslist.NotesListFrag;
import com.summer.desktop.module.rename.RenameFrag;
import com.summer.desktop.util.FragList;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseOpes;

import java.util.Random;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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
        getOpes().getUiOpe().init(fragment, this, this);
        getOpes().getUiOpe().getData(this, this);
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
        BoomFrag boomFrag = new BoomFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, boomFrag);
        transaction.commit();
        boomFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                int index = (int) o;
                final Note[] note = new Note[1];
                switch (index) {
                    case 0:
                        //note
                        note[0] = new Note(Note.NOTE, "新建笔记" + random.nextFloat() * 100);
                        note[0].setData(gson.toJson(new GsonNoteBean()));
                        note[0].setParentId(getOpes().getUiOpe().parentNote.getObjectId());
                        note[0].save(new SaveListener<String>() {

                            @Override
                            public void done(String objectId, BmobException e) {
                                getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                            }
                        });
                        break;
                    case 1:
                        note[0] = new Note(Note.NOTEBOOK, "新建笔记本" + random.nextFloat() * 100);
                        note[0].setParentId(getOpes().getUiOpe().parentNote.getObjectId());
                        note[0].save(new SaveListener<String>() {

                            @Override
                            public void done(String objectId, BmobException e) {
                                getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                            }
                        });
                        //notebook
                        break;
                    case 2:
                        //delete
                        if (v.getTag(R.id.data) == null) {
                            break;
                        }
                        note[0] = new Note();
                        note[0].setObjectId(((Note) (v.getTag(R.id.data))).getObjectId());
                        note[0].delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
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
                                String s = (String) o;
                                if (s.equals("")) {
                                    return;
                                }
                                note[0] = new Note();
                                note[0].setObjectId(((Note) (v.getTag(R.id.data))).getObjectId());
                                note[0].setName(s);
                                note[0].update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        getOpes().getUiOpe().getData(NoteListFrag.this, NoteListFrag.this);
                                    }
                                });
                            }
                        });
                        break;
                }
                if (index > 2) {

                } else {

                }
            }
        });
        return true;
    }
}
