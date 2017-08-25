package com.summer.desktop.module.note.note;

//by summer on 2017-07-31.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.bean.FileBean;
import com.android.lib.bean.FilesBean;
import com.android.lib.bean.LayoutDABean;
import com.android.lib.network.NetOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetProcessAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.lzy.imagepicker.bean.ImageItem;
import com.summer.desktop.module.note.bean.FileListBean;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.netdata.NoteDataI;
import com.summer.desktop.module.note.netdata.NoteDataOpe;
import com.summer.desktop.module.note.note.bean.NoteBean;
import com.summer.desktop.module.note.note.bean.NoteImageItemBean;
import com.summer.desktop.module.note.note.bean.NoteItemBean;
import com.summer.desktop.module.note.note.bean.NoteTxtItemBean;

import java.io.File;
import java.util.ArrayList;

public class NoteDAOpe extends BaseDAOpe {

    NoteOrBookBean notedata;

    ArrayList<NoteOrBookBean> noteOrBooks = new ArrayList<>();

    int position;

    NoteDataI noteDataOpe;

    ArrayList<LayoutDABean> data = new ArrayList<>();

    public NoteDAOpe(Context context) {
        super(context);
        noteDataOpe = new NoteDataOpe(context);
    }

    public ArrayList<LayoutDABean> getNoteList() {
        data.clear();
        NoteBean noteBean = notedata.getData();
        for (int i = 0; i < noteBean.getData().size(); i++) {
            LayoutDABean daBean = new LayoutDABean();
            data.add(daBean);
            daBean.aint.set(noteBean.getData().get(i).getType());
            daBean.dint.set(noteBean.getType());
            switch (noteBean.getData().get(i).getType()) {
                case NoteItemBean.TYPE_TXT:
                    NoteTxtItemBean noteTxtItemBean = GsonUtil.getInstance().fromJson(noteBean.getData().get(i).getData(), NoteTxtItemBean.class);
                    daBean.a.set(noteTxtItemBean.getTxt());
                    break;
                case NoteItemBean.TYPE_IMAGE:
                    NoteImageItemBean noteImageItemBean = GsonUtil.getInstance().fromJson(noteBean.getData().get(i).getData(), NoteImageItemBean.class);
                    daBean.a.set(noteImageItemBean.getSrc());
                    daBean.b.set(noteImageItemBean.getUrl());
                    daBean.cint.set(noteImageItemBean.getW());
                    daBean.bint.set(noteImageItemBean.getH());
                    break;
                default:

                    break;
            }
        }
        return data;
    }


    public void getNoteListData(ArrayList<LayoutDABean> data) {
        NoteBean noteBean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(notedata.getData()), NoteBean.class);
        for (int i = 0; i < data.size(); i++) {
            switch (data.get(i).aint.get()) {
                case NoteItemBean.TYPE_TXT:
                    NoteTxtItemBean noteTxtItemBean = GsonUtil.getInstance().fromJson(noteBean.getData().get(i).getData(), NoteTxtItemBean.class);
                    noteTxtItemBean.setTxt((String) data.get(i).a.get());
                    noteBean.getData().get(i).setData(GsonUtil.getInstance().toJson(noteTxtItemBean));
                    noteBean.getData().get(i).setType(NoteItemBean.TYPE_TXT);
                    break;
                case NoteItemBean.TYPE_IMAGE:
                    NoteImageItemBean noteImageItemBean = GsonUtil.getInstance().fromJson(noteBean.getData().get(i).getData(), NoteImageItemBean.class);
                    noteImageItemBean.setSrc((String) data.get(i).a.get());
                    noteImageItemBean.setUrl((String) data.get(i).b.get());
                    noteBean.getData().get(i).setType(NoteItemBean.TYPE_IMAGE);
                    noteBean.getData().get(i).setData(GsonUtil.getInstance().toJson(noteImageItemBean));
                    break;
                default:

                    break;
            }
        }
        notedata.setData(noteBean);
    }


    public NoteOrBookBean getNotedata() {
        return noteOrBooks.get(position);
    }

    public void setNotedata(NoteOrBookBean notedata) {
        this.notedata = notedata;
    }

    public NoteDataI getNoteDataOpe() {
        return noteDataOpe;
    }

    public void setNoteDataOpe(NoteDataOpe noteDataOpe) {
        this.noteDataOpe = noteDataOpe;
    }

    public void addTxt(final NetOpe.onNetProcess process) {
        NoteBean noteBean = notedata.getData();
        NoteTxtItemBean noteTxtItemBean = new NoteTxtItemBean();
        noteTxtItemBean.setTxt("new txt");
        NoteItemBean noteItemBean = new NoteItemBean();
        noteItemBean.setType(NoteItemBean.TYPE_TXT);
        noteItemBean.setData(GsonUtil.getInstance().toJson(noteTxtItemBean));
        noteBean.getData().add(noteItemBean);
        notedata.setData(noteBean);
        noteDataOpe.updateNote(notedata, process);
    }

    public void addImages(final ArrayList<ImageItem> imageItems, final NetOpe.onNetProcess process1, final NetOpe.onNetProcess process) {
        NoteBean noteBean = notedata.getData();
        for (int i = 0; i < imageItems.size(); i++) {
            NoteImageItemBean noteImageItemBean = new NoteImageItemBean();
            noteImageItemBean.setSrc(imageItems.get(i).path);
            noteImageItemBean.setW(imageItems.get(i).width);
            noteImageItemBean.setH(imageItems.get(i).height);
            NoteItemBean noteItemBean = new NoteItemBean();
            noteItemBean.setType(NoteItemBean.TYPE_IMAGE);
            noteItemBean.setData(GsonUtil.getInstance().toJson(noteImageItemBean));
            noteBean.getData().add(noteItemBean);
        }
        notedata.setData(noteBean);
        noteDataOpe.updateNote(notedata, new OnNetProcessAdapter<BaseResBean>() {
            @Override
            public void onResult(BaseResBean resBean) {
                if (!resBean.isException()) {
                    FilesBean files = new FilesBean();
                    for (int i = 0; i < imageItems.size(); i++) {
                        FileBean fileBean = new FileBean();
                        fileBean.setFile(new File(imageItems.get(i).path));
                        files.getData().add(fileBean);
                    }
//                    noteDataOpe.addFile(notedata,i,files, process);
                }
            }
        });

    }

    public void upateNote(final ArrayList<ImageItem> imageItems, final NetOpe.onNetProcess process) {
        NoteBean noteBean = notedata.getData();
        for (int i = 0; i < imageItems.size(); i++) {
            NoteImageItemBean noteImageItemBean = new NoteImageItemBean();
            noteImageItemBean.setSrc(imageItems.get(i).path);
            noteImageItemBean.setW(imageItems.get(i).width);
            noteImageItemBean.setH(imageItems.get(i).height);
            NoteItemBean noteItemBean = new NoteItemBean();
            noteItemBean.setType(NoteItemBean.TYPE_IMAGE);
            noteItemBean.setData(GsonUtil.getInstance().toJson(noteImageItemBean));
            noteBean.getData().add(noteItemBean);
        }
        notedata.setData(noteBean);
        noteDataOpe.updateNote(notedata, process);

    }

    public void getNoteDetail(final NetOpe.onNetProcess process) {
        noteDataOpe.getNoteDetail(notedata, process);
    }


    public void CheckImages(final NetOpe.onNetProcess process) {
        if (notedata == null || notedata.getData() == null || notedata.getData().getData() == null || notedata.getData().getData().size() == 0) {
            return;
        }
        for (int i = 0; i < notedata.getData().getData().size(); i++) {
            if (notedata.getData().getData().get(i).getType() == NoteItemBean.TYPE_IMAGE) {
                final NoteImageItemBean noteImageItemBean = GsonUtil.getInstance().fromJson(notedata.getData().getData().get(i).getData(), NoteImageItemBean.class);
                FileBean fileBean = new FileBean();
                File file = new File(noteImageItemBean.getSrc());
                if (file.exists() && NullUtil.isStrEmpty(noteImageItemBean.getUrl())) {
                    FilesBean files = new FilesBean();
                    fileBean.setFile(file);
                    files.getData().add(fileBean);
                    final int finalI = i;
                    noteDataOpe.addFile(i, files, new OnNetProcessAdapter<FileListBean>() {
                        @Override
                        public void onResult(FileListBean s) {
                            noteImageItemBean.setUrl(s.getData().get(0));
                            notedata.getData().getData().get(finalI).setData(GsonUtil.getInstance().toJson(noteImageItemBean));
                            noteDataOpe.updateNote(notedata, process);
                        }
                    });
                }
            }
        }
    }

    public void updateData(final NetOpe.onNetProcess process) {
        getNoteListData(data);
        noteDataOpe.updateNote(notedata, process);
    }

    public ArrayList<LayoutDABean> getData() {
        return data;
    }

    public void deleteItem(int position) {
        if (notedata != null && notedata.getData() != null && notedata.getData().getData() != null && notedata.getData().getData().size() > position) {
            notedata.getData().getData().remove(position);
        }
    }

    public ArrayList<NoteOrBookBean> getNoteOrBooks() {
        return noteOrBooks;
    }

    public void setNoteOrBooks(ArrayList<NoteOrBookBean> noteOrBooks) {
        this.noteOrBooks = noteOrBooks;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        this.notedata = noteOrBooks.get(position);
    }
}
