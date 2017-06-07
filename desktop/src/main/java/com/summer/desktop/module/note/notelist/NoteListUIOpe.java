package com.summer.desktop.module.note.notelist;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.uibean.NewsFragUIBean;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.view.ItemDecoration.MyItemDecoration2;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class NoteListUIOpe extends BaseUIOpe<NewsFragUIBean> {


    ArrayList<Note> notes = new ArrayList<>();

    Note parentNote;

    Gson gson = new Gson();


    public NoteListUIOpe(Context context) {
        super(context, new NewsFragUIBean(context, null));
    }

    public void init(Fragment fragment, View.OnClickListener listener, View.OnLongClickListener longClickListener) {
        getUiBean().getRecycle().setLayoutManager(new LinearLayoutManager(context));
        getUiBean().getRecycle().addItemDecoration(new MyItemDecoration2(context, 1));
        getUiBean().getNotelist().setOnLongClickListener(longClickListener);
        parentNote = (Note) fragment.getArguments().getSerializable("data");
    }

    public void getData(final View.OnClickListener listener, final View.OnLongClickListener onLongClickListener) {
        if (parentNote != null) {
            BmobQuery<Note> query = new BmobQuery<Note>();
            //查询playerName叫“比目”的数据
            query.addWhereEqualTo("parentId", parentNote.getObjectId());
            //返回50条数据，如果不加上这条语句，默认返回10条数据
            query.setLimit(50);
            //执行查询方法
            query.findObjects(new FindListener<Note>() {
                @Override
                public void done(List<Note> object, BmobException e) {
                    notes = (ArrayList<Note>) object;
                    getUiBean().getRecycle().setAdapter(new NewsAdapter(context, notes));
                    ((NewsAdapter) getUiBean().getRecycle().getAdapter()).setOnClickListener(listener);
                    ((NewsAdapter) getUiBean().getRecycle().getAdapter()).setOnLongClickListener(onLongClickListener);
                }
            });
        }
    }
}
