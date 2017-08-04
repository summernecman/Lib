package com.summer.desktop.module.note.netdata;

//by summer on 2017-07-28.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.bean.FilesBean;
import com.android.lib.network.NetOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.summer.desktop.module.base.bean.DesktopReqBean;
import com.summer.desktop.module.note.bean.NoteListResBean;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.bean.NoteReqBean;
import com.summer.desktop.module.note.note.bean.NoteBean;
import com.summer.desktop.module.note.note.bean.NoteItemBean;

import java.util.ArrayList;

public class NoteDataOpe extends BaseDAOpe implements NoteDataI {


    public NoteDataOpe(Context context) {
        super(context);
    }

    @Override
    public void getNoteList(Integer objectId, NetOpe.onNetProcess process) {

        NoteReqBean reqBean = new NoteReqBean();
        reqBean.setObjectId(objectId);
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        //NetWork.getInstance(context).doHttpRequsetWithSession(context,"/note/notelist",desktopReqBean,onNetWorkReqInterf);
        //NetWork.getInstance(context).doHttpRequsetWithSession(context,"/note/notelist",desktopReqBean, listener);
        NetOpe.getInstance().doHttpRequset(context, "/note/notelist", desktopReqBean, process, NoteListResBean.class);
    }

    @Override
    public void addNote(Integer parentId, NetOpe.onNetProcess process) {
        NoteOrBookBean reqBean = new NoteOrBookBean();
        reqBean.setParentId(parentId);
        reqBean.setName("new note");
        reqBean.setType(1);
        NoteBean noteBean = new NoteBean();
        noteBean.setData(new ArrayList<NoteItemBean>());
        reqBean.setData(noteBean);
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        NetOpe.getInstance().doHttpRequset(context, "/note/addnote", desktopReqBean, process, BaseResBean.class);
    }

    @Override
    public void addNoteBook(Integer parentId, NetOpe.onNetProcess process) {
        NoteOrBookBean reqBean = new NoteOrBookBean();
        reqBean.setParentId(parentId);
        reqBean.setName("new notebook");
        reqBean.setType(0);
        reqBean.setData(new NoteBean());
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        NetOpe.getInstance().doHttpRequset(context, "/note/addnotebook", desktopReqBean, process, BaseResBean.class);
    }

    @Override
    public void updateNote(NoteOrBookBean reqBean, NetOpe.onNetProcess process) {
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        NetOpe.getInstance().doHttpRequset(context, "/note/updatenote", desktopReqBean, process, BaseResBean.class);
    }

    @Override
    public void addFile(int position, FilesBean files, NetOpe.onNetProcess process) {
        NetOpe.getInstance().doHttpRequsetWithFile(context, "/note/addfiles", position, files, process);
    }

    @Override
    public void delteNote(NoteOrBookBean reqBean, NetOpe.onNetProcess process) {
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        NetOpe.getInstance().doHttpRequset(context, "/note/deletenote", desktopReqBean, process, BaseResBean.class);
    }

    @Override
    public void renameNote(NoteOrBookBean reqBean, NetOpe.onNetProcess process) {
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        NetOpe.getInstance().doHttpRequset(context, "/note/rename", desktopReqBean, process, BaseResBean.class);
    }

    @Override
    public void getNoteDetail(NoteOrBookBean reqBean, NetOpe.onNetProcess process) {
        DesktopReqBean desktopReqBean = new DesktopReqBean();
        desktopReqBean.setData(GsonUtil.getInstance().toJson(reqBean));
        NetOpe.getInstance().doHttpRequset(context, "/note/getnotedetail", desktopReqBean, process, NoteListResBean.class);
    }


}
