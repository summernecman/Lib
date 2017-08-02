package com.summer.desktop.module.note.note;

//by summer on 2017-07-31.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetProcessAdapter;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.summer.desktop.R;
import com.summer.desktop.module.note.bean.NoteOrBookBean;

import java.io.File;
import java.util.ArrayList;

public class NoteFrag extends BaseUIFrag<NoteUIOpe, NoteDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        P().D().setNotedata((NoteOrBookBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        P().U().fillNoteDetail(P().D().getNoteList(), this);
        P().U().init(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                switch (index) {
                    case 0:
                        IntentUtil.getInstance().photosShowFromphone(fragment, 0);
//                        RenameFrag renameFrag = new RenameFrag();
//                        FragmentUtil.getInstance().add(activity, R.id.homeroot,renameFrag);
//                        renameFrag.setOnfinish(new OnFinishListener() {
//                            @Override
//                            public void onFinish(Object o) {
//                                P().D().addImage(o.toString(),new OnNetWorkReqAdapter(activity) {
//                                    @Override
//                                    public void onNetWorkResult(boolean success, BaseResBean o) {
//                                        LogUtil.E(o);
//                                    }
//                                });
//                            }
//                        });
                        break;
                    case 1:
                        P().D().addTxt(new OnNetWorkReqAdapter(activity) {
                            @Override
                            public void onNetWorkResult(boolean success, BaseResBean o) {
                                LogUtil.E(o);
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
        P().D().updateData(new OnNetWorkReqAdapter(activity.getApplicationContext()) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 0) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                P().D().addImage(images, new OnNetProcessAdapter() {
                    @Override
                    public void onResult(Object o) {

                    }
                });
            }
        }
    }

    @Override
    public void onClick(View v) {
        ArrayList<String> list = new ArrayList<>();
        String s = "";
        String src = (String) v.getTag(R.id.data1);
        File file = new File(src);
        if (file.exists()) {
            s = src;
        } else {
            s = (String) v.getTag(R.id.data);
        }
        list.add(s);
        P().U().goToImagesViewPager(fragment, list, 0);

    }
}
