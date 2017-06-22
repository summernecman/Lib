package com.summer.desktop.module.day;

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
import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.bean.dabean.TimeBean;
import com.summer.desktop.module.circlemenu.CircleMenuFrag;
import com.summer.desktop.module.note.rename.RenameFrag;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.FragmentUtil;
import com.summer.lib.util.NullUtil;
import com.summer.lib.util.data.DateFormatUtil;
import com.summer.lib.util.file.TimePickUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class DayFrag extends BaseUIFrag<DayMainUIOpe, DayMainDAOpe> implements DayView3.OnlongClickWithHM {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUi().initList(this);
        BroadcastReceiver receiver = new DayBroadCase();
        IntentFilter filter = new IntentFilter();
        filter.addAction(activity.getPackageName() + ValueConstant.ACITON_GLOB_CAST);
        activity.registerReceiver(receiver, filter);
        initData();
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
                                getOpes().getDa().getTimes().add(timeBean);
                                getOpes().getUi().addTimes(getOpes().getDa().getTimes());
                                getOpes().getDa().getNoteListDAOpe().createTodayNote(timeBean, "0",
                                        new String[]{
                                                Calendar.getInstance().get(Calendar.YEAR) + "年",
                                                (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月",
                                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日"},
                                        timeBean.text,
                                        new OnFinishListener() {
                                            @Override
                                            public void onFinish(Object o) {
                                                getOpes().getDa().getData().put(timeBean.toString(), (String) o);
                                                initData();
                                            }
                                        });
                            }
                        });
                        break;
                    case 2:
                        getOpes().getDa().getDayDBOpe().delete(getOpes().getUi().viewDataBinding.dayview2.getArea(h, m));
                        getOpes().getDa().delete(getOpes().getUi().viewDataBinding.dayview2.getArea(h, m), new OnFinishListener() {
                            @Override
                            public void onFinish(Object o) {
                                initData();
                            }
                        });
                        //getOpes().getUi().deleteTime(h, m);
                        break;
                    case 3:
                        final String area = getOpes().getUi().viewDataBinding.dayview2.getArea(h, m);
                        if (NullUtil.isStrEmpty(area)) {
                            break;
                        }
                        if (!area.startsWith(TimeBean.TYPE_EVERYDAY)) {
                            getOpes().getUi().goToNote(activity, getOpes().getDa().getData().get(area));
                        } else {
                            RenameFrag renameFrag = new RenameFrag();
                            FragmentUtil.getInstance().add(getActivity(), R.id.root_day, renameFrag);
                            renameFrag.setOnfinish(new OnFinishListener() {
                                @Override
                                public void onFinish(Object o) {
                                    getOpes().getDa().dayDBOpe.updateName(area, (String) o);
                                    initData();
                                }
                            });
                        }
                        break;
                    case 4:
                        TimePickUtil.getInstance().showTimePickDialogHMHM(activity, getFragmentManager(), new TimePickUtil.TimeDate() {
                            @Override
                            public void getTimeData(ArrayList<Long> time) {
                                final TimeBean timeBean = new TimeBean(DateFormatUtil.getHour(time.get(0)), DateFormatUtil.getMinute(time.get(0)), DateFormatUtil.getHour(time.get(1)), DateFormatUtil.getMinute(time.get(1)), TimeBean.TYPE_EVERYDAY);
                                getOpes().getDa().getTimes().add(timeBean);
                                getOpes().getUi().addTimes(getOpes().getDa().getTimes());
                                getOpes().getDa().getDayDBOpe().add(timeBean);
                            }
                        });
                        break;
                    case 1:
                        initData();
                        break;
                }
            }
        });
    }

    public void initData() {
        getOpes().getDa().getTodayNotes(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                Object[] o1 = (Object[]) o;
                ArrayList<TimeBean> times = (ArrayList<TimeBean>) o1[1];
                getOpes().getDa().getTimes().clear();
                getOpes().getDa().initTimeBean(times);
                getOpes().getUi().addTimes(getOpes().getDa().getTimes());
                for (int i = 0; i < times.size(); i++) {
                    getOpes().getDa().getData().put(times.get(i).toString(), (String) o1[0]);
                }
                getOpes().getDa().setNotes((ArrayList<Note>) o1[2]);
                getOpes().getUi().viewDataBinding.dayview2.refresh();
            }
        });
    }

    public class DayBroadCase extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //getOpes().getUiOpe().getUiBean().getRecycle().getAdapter().notifyDataSetChanged();
            getOpes().getUi().viewDataBinding.dayview2.refresh();
        }
    }
}
