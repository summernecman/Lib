package com.summer.desktop.module.note.note;

//by summer on 2017-07-31.

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.LayoutDABean;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.NetOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetProcessAdapter;
import com.android.lib.util.IntentUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.summer.desktop.R;
import com.summer.desktop.module.circlemenu.CircleMenuFrag;
import com.summer.desktop.module.note.bean.NoteListResBean;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.note.bean.NoteBean;

import java.io.File;
import java.util.ArrayList;

public class NoteFrag extends BaseUIFrag<NoteUIOpe, NoteDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        getP().getD().setNoteOrBooks((ArrayList<NoteOrBookBean>) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().setPosition(getArguments().getInt(ValueConstant.DATA_POSITION));
        getP().getU().fillNoteDetail(getP().getD().getNoteList(), getP().getD().getNotedata().getData().getType(), this);
        getP().getU().init(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                switch (getP().getD().getNotedata().getData().getType()) {
                    case NoteBean.NOTE_TYPE_GALLERY:
                        switch (index) {
                            case 0:
                                break;
                            default:
                                return;
                        }
                }
                switch (index) {
                    case 0:
                        IntentUtil.getInstance().photosShowFromphone(fragment, 0);
                        break;
                    case 1:
                        getP().getD().addTxt(new OnNetProcessAdapter<BaseResBean>() {
                            @Override
                            public void onResult(BaseResBean resBean) {
                                getP().getD().getNoteDetail(new OnNetProcessAdapter<NoteListResBean>() {
                                    @Override
                                    public void onResult(NoteListResBean resBean) {
                                        if (resBean.getData() != null && resBean.getData().size() > 0) {
                                            getP().getD().setNotedata(resBean.getData().get(0));
                                            getP().getU().fillNoteDetail(getP().getD().getNoteList(), getP().getD().getNotedata().getData().getType(), NoteFrag.this);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getP().getD().CheckImages(new OnNetProcessAdapter());
        getP().getD().updateData(new OnNetProcessAdapter());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 0) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                getP().getD().upateNote(images, new OnNetProcessAdapter<BaseResBean>() {
                    @Override
                    public void onResult(BaseResBean resBean) {
                        getP().getD().CheckImages(new OnNetProcessAdapter());
                        getP().getD().getNoteDetail(new OnNetProcessAdapter<NoteListResBean>() {
                            @Override
                            public void onResult(NoteListResBean resBean) {
                                if (resBean.getData() != null && resBean.getData().size() > 0) {
                                    getP().getD().setNotedata(resBean.getData().get(0));
                                    getP().getU().fillNoteDetail(getP().getD().getNoteList(), getP().getD().getNotedata().getData().getType(), NoteFrag.this);
                                }
                            }
                        });
                    }
                });
            }
        }
    }


    @Override
    public void onInterupt(int type, final View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                ArrayList<LayoutDABean> data = (ArrayList<LayoutDABean>) v.getTag(R.id.data);
                ArrayList<String> list = new ArrayList<>();
                String s = "";
                for (int i = 0; i < data.size(); i++) {
                    File file = new File(data.get(i).a.get().toString());
                    s = file.getPath();
                    if (!file.exists()) {
                        s = NetOpe.NET_DOMAIN + "/files/" + data.get(i).b;
                    }
                    list.add(s);
                }
                getP().getU().goToImagesViewPager(fragment, list, (Integer) v.getTag(R.id.position));
                break;
            case ViewListener.TYPE_ONLONGCLICK:
                CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.homeroot, circleMenuFrag);
                transaction.commit();
                circleMenuFrag.setOnFinishListener(new OnFinishListener<Integer>() {
                    @Override
                    public void onFinish(Integer o) {
                        switch (o) {
                            case 2:
                                int position = (int) v.getTag(R.id.position);
                                getP().getD().deleteItem(position);
                                getP().getU().fillNoteDetail(getP().getD().getNoteList(), getP().getD().getNotedata().getData().getType(), NoteFrag.this);
                                getP().getD().CheckImages(new OnNetProcessAdapter());
                                getP().getD().updateData(new OnNetProcessAdapter());
                                break;
                        }
                    }
                });
                break;
        }
    }
}
