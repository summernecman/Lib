package com.summer.desktop.module.note.notedetail;

//by summer on 2017-06-06.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.summer.desktop.bean.dabean.BoomMsg;
import com.summer.desktop.bean.dabean.NoteDetail;
import com.summer.desktop.bean.dabean.TxtNote;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.util.IntentUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.summer.desktop.util.ViewCreater.gson;

public class NoteDetailFrag extends BaseUIFrag<NoteDetailUIOpe, NoteDetailDAOpe> {


    @Override
    public BaseOpes<NoteDetailUIOpe, NoteDetailDAOpe> createOpes() {
        return new BaseOpes<>(new NoteDetailUIOpe(activity), new NoteDetailDAOpe(activity));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDaOpe().init(fragment);
        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean);
        getOpes().getUiOpe().init(fragment, getOpes().getDaOpe().bean, new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                // When the boom-button corresponding this builder is clicked.
                Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                switch (index) {
                    case 0:
                        IntentUtil.getInstance().photoShowFromphone(fragment, 0);
                        break;
                    case 1:
                        getOpes().getDaOpe().bean.getData().add(new NoteDetail(NoteDetail.TXT, gson.toJson(new TxtNote("new\\n"))));
                        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean);
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
        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        int[] ints = new int[]{0};
        getOpes().getDaOpe().update(ints, getOpes().getDaOpe().bean, getOpes().getDaOpe().note);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        getOpes().getDaOpe().onresult(data, getOpes().getDaOpe().bean);
        getOpes().getUiOpe().getData(fragment, getOpes().getDaOpe().bean);
    }
}
