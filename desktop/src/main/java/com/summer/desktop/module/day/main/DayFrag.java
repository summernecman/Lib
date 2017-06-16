package com.summer.desktop.module.day.main;

//by summer on 2017-06-13.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.module.day.dayview.DayView;
import com.summer.desktop.module.note.circlemenu.CircleMenuFrag;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.data.DateFormatUtil;
import com.summer.lib.util.file.TimePickUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class DayFrag extends BaseUIFrag<DayMainUIOpe, DayMainDAOpe> implements DayView.OnlongClickWithHM {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUiOpe().initList(this);
        BroadcastReceiver receiver = new DayBroadCase();
        IntentFilter filter = new IntentFilter();
        filter.addAction(activity.getPackageName() + ValueConstant.ACITON_GLOB_CAST);
        activity.registerReceiver(receiver, filter);
        getOpes().getDaOpe().getTodayNotes(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                Object[] o1 = (Object[]) o;
                ArrayList<TimeBean> times = (ArrayList<TimeBean>) o1[1];
                getOpes().getDaOpe().getTimes().clear();
                getOpes().getDaOpe().getTimes().addAll(times);
                getOpes().getUiOpe().addTimes(getOpes().getDaOpe().getTimes());
                for (int i = 0; i < times.size(); i++) {
                    getOpes().getDaOpe().getData().put(times.get(i).toString(), (String) o1[0]);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onLongClick(View v, final int h, final int m) {
        CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.homeroot, circleMenuFrag);
        transaction.commit();
        circleMenuFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                switch ((int) o) {
                    case 0:
                        TimePickUtil.getInstance().showTimePickDialogHMHM(activity, getFragmentManager(), new TimePickUtil.TimeDate() {
                            @Override
                            public void getTimeData(ArrayList<Long> time) {
                                final TimeBean timeBean = new TimeBean(DateFormatUtil.getHour(time.get(0)), DateFormatUtil.getMinute(time.get(0)), DateFormatUtil.getHour(time.get(1)), DateFormatUtil.getMinute(time.get(1)));
                                getOpes().getDaOpe().getTimes().add(timeBean);
                                getOpes().getUiOpe().addTimes(getOpes().getDaOpe().getTimes());
                                getOpes().getDaOpe().getNoteListDAOpe().createTodayNote(timeBean, "0",
                                        new String[]{
                                                Calendar.getInstance().get(Calendar.YEAR) + "年",
                                                (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月",
                                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日"},
                                        timeBean.text,
                                        new OnFinishListener() {
                                            @Override
                                            public void onFinish(Object o) {
                                                getOpes().getDaOpe().getData().put(timeBean.toString(), (String) o);
                                            }
                                        });
                            }
                        });
                        break;
                    case 1:
                        getOpes().getDaOpe().getTodayNotes(new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                Object[] o1 = (Object[]) o;
                                ArrayList<TimeBean> times = (ArrayList<TimeBean>) o1[1];
                                getOpes().getDaOpe().getTimes().clear();
                                getOpes().getDaOpe().getTimes().addAll(times);
                                getOpes().getUiOpe().addTimes(getOpes().getDaOpe().getTimes());
                                for (int i = 0; i < times.size(); i++) {
                                    getOpes().getDaOpe().getData().put(times.get(i).toString(), (String) o1[0]);
                                }
                            }
                        });
                        break;
                    case 2:
                        getOpes().getUiOpe().deleteTime(h, m);
                        break;
                    case 3:
                        String area = getOpes().getUiOpe().getUiBean().getDayview().getArea(h, m);
                        if (getOpes().getDaOpe().getData().get(area) != null) {
                            getOpes().getUiOpe().goToNote(activity, getOpes().getDaOpe().getData().get(area));
                        }
                        break;
                }
            }
        });
    }

    public class DayBroadCase extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //getOpes().getUiOpe().getUiBean().getRecycle().getAdapter().notifyDataSetChanged();
            getOpes().getUiOpe().getUiBean().getDayview().refresh();
        }
    }
}
