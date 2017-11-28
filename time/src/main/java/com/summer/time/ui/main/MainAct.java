package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.SheetDialogUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.view.bottomdialogmenuview.BottomDialogMenuView;
import com.summer.time.R;
import com.summer.time.ui.main.note.NoteFrag;
import com.summer.time.ui.main.thing.ThingFrag;
import com.summer.time.ui.main.thingview.ThingBean;
import com.summer.time.ui.main.timeview.TimeAreaBean;

import butterknife.OnClick;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> {

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initTime(getP().getD().initFrags());
        getP().getU().updateTime((ThingFrag) getP().getD().getFragments().get(0), (NoteFrag) getP().getD().getFragments().get(1));
        ((ThingFrag) getP().getD().getFragments().get(0)).setDeleteListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                final View v = (View) o;
                getP().getU().showDeleteDilalog(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        ThingFrag thingFrag = ((ThingFrag) getP().getD().getFragments().get(0));
                        int pos = (int) v.getTag(R.id.position);
                        thingFrag.getP().getD().getThingBeans().remove(pos);
                        thingFrag.getP().getU().initThing(thingFrag.getP().getD().getThingBeans());
                    }
                });
            }
        });
        getP().getU().bind.time.setUpdateListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                HandleUtil.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        ThingFrag thingFrag = ((ThingFrag) getP().getD().getFragments().get(0));
                        thingFrag.getP().getU().scrollToPos(thingFrag.getP().getD().findNowThing());
                    }
                });
            }
        });
    }

    @OnClick({R.id.iv_menu})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                String[] s = new String[]{"新增", "查询", "取消"};
                BottomDialogMenuView bottomDialogMenuView = new BottomDialogMenuView(activity, s);
                SheetDialogUtil.getInstance().showBottomSheet(activity, bottomDialogMenuView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = (int) (v.getTag(R.id.position));
                        switch (i) {
                            case 0:
                                getP().getU().selctTimeArea(new OnFinishListener() {
                                    @Override
                                    public void onFinish(Object o) {
                                        TimeAreaBean timeAreaBean = (TimeAreaBean) o;
                                        ThingBean thingBean = new ThingBean();
                                        thingBean.setTimeArea(timeAreaBean);
                                        thingBean.setThing("");
                                        thingBean.setHeight(50);
                                        thingBean.setPos(0);
                                        ((ThingFrag) getP().getD().getFragments().get(0)).getP().getD().add(thingBean);
                                        ((ThingFrag) getP().getD().getFragments().get(0)).getP().getU().initThing(((ThingFrag) getP().getD().getFragments().get(0)).getP().getD().getThingBeans());
                                        //getP().getU().bind.vpVp.setCurrentItem(1);
                                        ((NoteFrag) getP().getD().getFragments().get(1)).getP().getU().bind.etTxt.setEnabled(true);
                                    }
                                });
                                break;
                        }
                        SheetDialogUtil.getInstance().dismess();
                    }
                });
                break;
        }
    }
}
