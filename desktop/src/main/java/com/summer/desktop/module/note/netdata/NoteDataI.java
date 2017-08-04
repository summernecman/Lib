package com.summer.desktop.module.note.netdata;

//by summer on 2017-07-28.

import com.android.lib.bean.FilesBean;
import com.android.lib.network.NetOpe;
import com.summer.desktop.module.note.bean.NoteOrBookBean;

public interface NoteDataI {

    public void getNoteList(Integer objectId, NetOpe.onNetProcess process);

    public void addNote(Integer parentId, NetOpe.onNetProcess process);

    public void addNoteBook(Integer parentId, NetOpe.onNetProcess process);

    public void updateNote(NoteOrBookBean reqBean, NetOpe.onNetProcess process);

    public void addFile(int position, FilesBean files, NetOpe.onNetProcess process);

    public void delteNote(NoteOrBookBean reqBean, NetOpe.onNetProcess process);

    public void renameNote(NoteOrBookBean reqBean, NetOpe.onNetProcess process);

    public void getNoteDetail(NoteOrBookBean reqBean, NetOpe.onNetProcess process);

}
