package com.summer.desktop.module.note.note;

//by summer on 2017-07-31.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.bean.FileBean;
import com.android.lib.bean.FilesBean;
import com.android.lib.bean.LayoutDABean;
import com.android.lib.network.NetOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.lzy.imagepicker.bean.ImageItem;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.netdata.NoteDataOpe;
import com.summer.desktop.module.note.note.bean.NoteBean;
import com.summer.desktop.module.note.note.bean.NoteImageItemBean;
import com.summer.desktop.module.note.note.bean.NoteItemBean;
import com.summer.desktop.module.note.note.bean.NoteTxtItemBean;

import java.io.File;
import java.util.ArrayList;

public class NoteDAOpe extends BaseDAOpe {

    NoteOrBookBean notedata;

    NoteDataOpe noteDataOpe;

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
            switch (noteBean.getData().get(i).getType()) {
                case NoteItemBean.TYPE_TXT:
                    NoteTxtItemBean noteTxtItemBean = GsonUtil.getInstance().fromJson(noteBean.getData().get(i).getData(), NoteTxtItemBean.class);
                    daBean.a.set(noteTxtItemBean.getTxt());
                    break;
                case NoteItemBean.TYPE_IMAGE:
                    NoteImageItemBean noteImageItemBean = GsonUtil.getInstance().fromJson(noteBean.getData().get(i).getData(), NoteImageItemBean.class);
                    daBean.a.set(noteImageItemBean.getSrc());
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
        return notedata;
    }

    public void setNotedata(NoteOrBookBean notedata) {
        this.notedata = notedata;
    }

    public NoteDataOpe getNoteDataOpe() {
        return noteDataOpe;
    }

    public void setNoteDataOpe(NoteDataOpe noteDataOpe) {
        this.noteDataOpe = noteDataOpe;
    }

    public void addTxt(OnNetWorkReqInterf onNetWorkReqInterf) {
        NoteBean noteBean = notedata.getData();
        NoteTxtItemBean noteTxtItemBean = new NoteTxtItemBean();
        noteTxtItemBean.setTxt("new txt");
        NoteItemBean noteItemBean = new NoteItemBean();
        noteItemBean.setType(NoteItemBean.TYPE_TXT);
        noteItemBean.setData(GsonUtil.getInstance().toJson(noteTxtItemBean));
        noteBean.getData().add(noteItemBean);
        notedata.setData(noteBean);
        noteDataOpe.updateNote(notedata, onNetWorkReqInterf);
    }

    public void addImage(final ArrayList<ImageItem> imageItems, final NetOpe.onNetProcess process) {
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
        noteDataOpe.updateNote(notedata, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {
                if (success) {
                    FilesBean files = new FilesBean();
                    for (int i = 0; i < imageItems.size(); i++) {
                        FileBean fileBean = new FileBean();
                        fileBean.setFile(new File(imageItems.get(i).path));
                        files.getData().add(fileBean);
                    }
                    noteDataOpe.addFile(files, process);
                }
            }
        });

    }

    public void updateData(OnNetWorkReqInterf onNetWorkReqInterf) {
        getNoteListData(data);
        noteDataOpe.updateNote(notedata, onNetWorkReqInterf);
    }

    public ArrayList<LayoutDABean> getData() {
        return data;
    }
}
