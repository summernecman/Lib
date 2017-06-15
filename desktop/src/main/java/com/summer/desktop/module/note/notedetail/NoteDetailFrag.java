package com.summer.desktop.module.note.notedetail;

//by summer on 2017-06-06.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.BoomMsg;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.ImageNote;
import com.summer.desktop.bean.dabean.NoteDetail;
import com.summer.desktop.bean.dabean.TxtNote;
import com.summer.desktop.module.note.circlemenu.CircleMenuFrag;
import com.summer.desktop.util.FragList;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.bean.databean.EventBean;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.GsonUtil;
import com.summer.lib.util.IntentUtil;
import com.summer.lib.view.image.imagepager.ImagePagerFrag;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

import static com.summer.desktop.util.ViewCreater.gson;

public class NoteDetailFrag extends BaseUIFrag<NoteDetailUIOpe, NoteDetailDAOpe> implements OnFinishListener {

    @Override
    public BaseOpes<NoteDetailUIOpe, NoteDetailDAOpe> createOpes() {
        return new BaseOpes<>(new NoteDetailUIOpe(activity), new NoteDetailDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDaOpe().init(fragment);
        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean, this, this);
        getOpes().getUiOpe().init(fragment, getOpes().getDaOpe().bean, new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                switch (index) {
                    case 0:
                        IntentUtil.getInstance().photosShowFromphone(fragment, 0);
                        break;
                    case 1:
                        switch (getOpes().getDaOpe().bean.getType()) {
                            case GsonNoteBean.TYPE_GALLERY:

                                break;
                            default:
                                getOpes().getDaOpe().bean.getData().add(new NoteDetail(NoteDetail.TXT, gson.toJson(new TxtNote("new\\n"))));
                                getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean, NoteDetailFrag.this, NoteDetailFrag.this);
                                break;
                        }
                        break;
                    case 3:
//                        control.dc.bean.getData().add(new NoteDetail(NoteDetail.LINK,gson.toJson(new LinkNote("http://www.baidu.com"))));
//                        control.vc.getData(fragment,control.dc.bean);
                        break;
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onboom(BoomMsg boomMsg) {
        getOpes().getDaOpe().bean.getData().remove(boomMsg.postion);
        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean, this, NoteDetailFrag.this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        int[] ints = new int[]{0};
        getOpes().getDaOpe().update(ints, getOpes().getDaOpe().bean, getOpes().getDaOpe().note);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        getOpes().getDaOpe().onresult(data, getOpes().getDaOpe().bean);
        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean, this, NoteDetailFrag.this);
    }

    @Override
    public void onFinish(final Object o) {
        final CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
        FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, circleMenuFrag);
        transaction.commit();
        circleMenuFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object b) {
                getOpes().getDaOpe().dealItemLongClick(fragment, (EventBean) o, (int) b);

            }
        });
    }

    @Override
    public void onClick(View v) {
        ArrayList<NoteDetail> data = (ArrayList<NoteDetail>) v.getTag(R.id.data1);
        int postion = (int) v.getTag(R.id.position);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType().equals(NoteDetail.IMAGE)) {
                ImageNote imageNote = GsonUtil.getInstance().fromJson(data.get(i).getData(), ImageNote.class);
                String url = "";
                if (imageNote.getLocalSrc() != null && imageNote.getLocalSrc().startsWith("file://")) {
                    File file = new File(imageNote.getLocalSrc().substring("file://".length(), imageNote.getLocalSrc().length()));
                    if (file.exists()) {
                        url = imageNote.getLocalSrc();
                    } else {
                        url = imageNote.getSrc();
                    }
                } else {
                    url = imageNote.getSrc();
                }
                strings.add(url);
            }
        }
        ImagePagerFrag pagerFrag = new ImagePagerFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ValueConstant.DATA_DATA, strings);
        bundle.putSerializable(ValueConstant.DATA_POSITION, postion);
        pagerFrag.setArguments(bundle);
        FragList.getInstance().add(getActivity(), pagerFrag);

    }
}
