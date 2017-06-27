package com.summer.desktop.module.note.notelist;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.ope.BaseUIBean;
import com.android.lib.util.LogUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.databinding.FragNoteNewsBinding;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class NoteListUIOpe extends BaseUIBean<FragNoteNewsBinding> {


    ArrayList<Note> notes = new ArrayList<>();

    Note parentNote;


    public NoteListUIOpe(Context context) {
        super(context);
    }


    public void init(Fragment fragment, View.OnClickListener listener, View.OnLongClickListener longClickListener, MaterialRefreshListener refreshListener) {
        viewDataBinding.recycle.setLayoutManager(new LinearLayoutManager(context));
        //viewDataBinding.recycle.addItemDecoration(new MyItemDecoration2(context, 3));
        viewDataBinding.notelist.setOnLongClickListener(longClickListener);
        parentNote = (Note) fragment.getArguments().getSerializable("data");
        viewDataBinding.refresh.setMaterialRefreshListener(refreshListener);
    }

    public void getData(final Fragment fragment, final View.OnLongClickListener onLongClickListener) {
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
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
                            LogUtil.E(fragment.getId());
                            viewDataBinding.recycle.setAdapter(new NewsAdapter(context, parentNote.getObjectId(), notes));
                            ((NewsAdapter) viewDataBinding.recycle.getAdapter()).setOnLongClickListener(onLongClickListener);
                        }
                    });
                }
            }
        }, 300);
    }
}
