package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.content.Context;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SheetDialogUtil;
import com.android.lib.view.bottomdialogmenuview.BottomDialogMenuView;
import com.android.lib.view.pickerview.TimePickerDialog;
import com.android.lib.view.pickerview.data.Type;
import com.android.lib.view.pickerview.listener.OnDateSetListener;
import com.summer.time.R;
import com.summer.time.databinding.ActMainBinding;
import com.summer.time.ui.main.note.NoteFrag;
import com.summer.time.ui.main.thing.ThingFrag;
import com.summer.time.ui.main.thingview.ThingBean;
import com.summer.time.ui.main.timeview.TimeAreaBean;
import com.summer.time.ui.main.timeview.TimeBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {


    public MainUIOpe(Context context) {
        super(context);
    }

    public void initTime(final ArrayList<BaseUIFrag> fragments) {
        bind.vpVp.init(fragments);
    }

    public void updateTime(ThingFrag thingFrag, final NoteFrag noteFrag) {
        thingFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ThingBean thingBean = (ThingBean) o;
                LogUtil.E(thingBean.toString());
                bind.time.UpdateThingArea(thingBean);
                //bind.time.UpdateThingArea(0,05,22,45);
                noteFrag.getP().getU().initTxt(thingBean);
            }
        });
    }

    public void selctTimeArea(final OnFinishListener onFinishListener) {
        final MainAct act = (MainAct) context;
        final TimeAreaBean timeAreaBean = new TimeAreaBean();
        TimePickerDialog.Builder builder = new TimePickerDialog.Builder();
        builder.setType(Type.HOURS_MINS).setCallBack(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                Date date = new Date(millseconds);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                LogUtil.E(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                timeAreaBean.setStart(new TimeBean(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
                TimePickerDialog.Builder builder = new TimePickerDialog.Builder();
                builder.setType(Type.HOURS_MINS).setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        Date date = new Date(millseconds);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        LogUtil.E(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                        timeAreaBean.setEnd(new TimeBean(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
                        onFinishListener.onFinish(timeAreaBean);

                    }
                });
                TimePickerDialog timePickerDialog = builder.build();
                timePickerDialog.show(act.getSupportFragmentManager(), "select");
            }
        });
        TimePickerDialog timePickerDialog = builder.build();
        timePickerDialog.show(act.getSupportFragmentManager(), "select");
    }

    public void showDeleteDilalog(final OnFinishListener listener) {
        String[] s = new String[]{"删除", "取消"};
        BottomDialogMenuView bottomDialogMenuView = new BottomDialogMenuView(context, s);
        SheetDialogUtil.getInstance().showBottomSheet(context, bottomDialogMenuView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) (v.getTag(R.id.position));
                switch (i) {
                    case 0:
                        listener.onFinish(v);
                        break;
                }
                SheetDialogUtil.getInstance().dismess();
            }
        });
    }


}
