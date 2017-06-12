package com.summer.desktop.module.note.notelist;

//by summer on 2017-06-06.

import android.content.Context;

import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.Note;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseDAOpe;
import com.summer.lib.util.GsonUtil;
import com.summer.lib.util.RandomUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class NoteListDAOpe extends BaseDAOpe {


    public NoteListDAOpe(Context context) {
        super(context);
    }

    public void createNote(String parentid, final OnFinishListener onFinishListener) {
        Note note = new Note(Note.NOTE, "新建笔记" + RandomUtil.getInstance().nextFloat() * 100);
        note.setData(GsonUtil.getInstance().toJson(new GsonNoteBean()));
        note.setParentId(parentid);
        note.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                onFinishListener.onFinish(objectId);
            }
        });
    }

    public void createNoteBook(String parentid, final OnFinishListener onFinishListener) {
        Note note = new Note(Note.NOTEBOOK, "新建笔记本" + RandomUtil.getInstance().nextFloat() * 100);
        note.setParentId(parentid);
        note.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                onFinishListener.onFinish(objectId);
            }
        });
    }

    public void deleteNote(Note n, final OnFinishListener onFinishListener) {
        if (n == null) {
            return;
        }
        Note note = new Note();
        note.setObjectId(n.getObjectId());
        note.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onFinishListener.onFinish(e);
            }
        });
    }


    public void renameNote(String s, Note n, final OnFinishListener onFinishListener) {
        if (s.equals("")) {
            return;
        }
        if (n == null) {
            return;
        }
        Note note = new Note();
        note.setObjectId(n.getObjectId());
        note.setName(s);
        note.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                onFinishListener.onFinish(e);
            }
        });
    }
}
