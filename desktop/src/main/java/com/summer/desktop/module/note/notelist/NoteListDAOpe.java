package com.summer.desktop.module.note.notelist;

//by summer on 2017-06-06.

import android.content.Context;

import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.Note;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseDAOpe;
import com.summer.lib.util.GsonUtil;
import com.summer.lib.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class NoteListDAOpe extends BaseDAOpe {


    int i = -1;

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

    public void createTodayNote(final String parentid, String[] name, final String finalname, final OnFinishListener onFinishListener) {
        i = -1;
        todayNoteBookExist(parentid, name, new OnFinishListener() {
            @Override
            public void onFinish(final Object o) {
                Note note = new Note(Note.NOTE, finalname);
                note.setData(GsonUtil.getInstance().toJson(new GsonNoteBean()));
                note.setParentId((String) o);
                note.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        onFinishListener.onFinish((String) o);
                    }
                });
            }
        });
    }

    public void todayNoteBookExist(final String parentid, final String[] name, final OnFinishListener onFinishListener) {
        i++;
        if (name.length <= i) {
            onFinishListener.onFinish(parentid);
            return;
        }
        BmobQuery<Note> query = new BmobQuery<>();
        query.addWhereEqualTo("parentId", parentid);
        query.addWhereEqualTo("name", name[i]);
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e != null) {
                    onFinishListener.onFinish(null);
                    return;
                }
                if (list == null) {
                    list = new ArrayList<Note>();
                }
                if (list.size() == 0) {
                    createNoteBookWithName(parentid, name[i], new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {
                            todayNoteBookExist((String) o, name, onFinishListener);
                        }
                    });
                } else {
                    todayNoteBookExist(list.get(0).getObjectId(), name, onFinishListener);
                }
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

    public void createNoteBookWithName(String parentid, String name, final OnFinishListener onFinishListener) {
        Note note = new Note(Note.NOTEBOOK, name);
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
