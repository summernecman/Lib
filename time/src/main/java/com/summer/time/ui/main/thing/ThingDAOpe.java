package com.summer.time.ui.main.thing;

//by summer on 2017-11-24.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.summer.time.ui.main.thingview.ThingBean;
import com.summer.time.ui.main.thingview.ThingView;
import com.summer.time.ui.main.timeview.TimeAreaBean;
import com.summer.time.ui.main.timeview.TimeBean;

import java.util.ArrayList;
import java.util.Calendar;

public class ThingDAOpe extends BaseDAOpe {

    ArrayList<ThingBean> thingBeans = new ArrayList<>();


    public ThingDAOpe(Context context) {
        super(context);
    }

    public ArrayList<ThingBean> initThings() {
        thingBeans.clear();
        thingBeans.add(new ThingBean("睡觉", "zzzzzzzzz", new TimeAreaBean(new TimeBean(0, 0), new TimeBean(8, 30)), 0, 50));
        thingBeans.add(new ThingBean("上班", "上班啊啊啊啊", new TimeAreaBean(new TimeBean(9, 0), new TimeBean(12, 00)), 0, 50));
        thingBeans.add(new ThingBean("点外卖", "点外卖", new TimeAreaBean(new TimeBean(11, 0), new TimeBean(11, 05)), 0, 50));
        thingBeans.add(new ThingBean("午饭", "午饭", new TimeAreaBean(new TimeBean(12, 0), new TimeBean(12, 20)), 0, 50));
        thingBeans.add(new ThingBean("午休", "午休", new TimeAreaBean(new TimeBean(12, 25), new TimeBean(13, 0)), 0, 50));
        thingBeans.add(new ThingBean("工作", "工作工作工作", new TimeAreaBean(new TimeBean(13, 0), new TimeBean(18, 20)), 0, 50));
        thingBeans.add(new ThingBean("提交日志", "回家回家回家", new TimeAreaBean(new TimeBean(18, 20), new TimeBean(18, 30)), 0, 50));
        thingBeans.add(new ThingBean("回家", "回家回家回家", new TimeAreaBean(new TimeBean(18, 30), new TimeBean(18, 40)), 0, 50));
        thingBeans.add(new ThingBean("超市买东西", "超市买东西超市买东西超市买东西超市买东西", new TimeAreaBean(new TimeBean(18, 40), new TimeBean(19, 0)), 0, 50));
        thingBeans.add(new ThingBean("做饭", "做饭做饭做饭", new TimeAreaBean(new TimeBean(19, 0), new TimeBean(19, 30)), 0, 50));
        thingBeans.add(new ThingBean("吃饭", "吃饭吃饭吃饭", new TimeAreaBean(new TimeBean(19, 30), new TimeBean(20, 20)), 0, 50));
        thingBeans.add(new ThingBean("锻炼路上", "锻炼路上锻炼路上", new TimeAreaBean(new TimeBean(20, 30), new TimeBean(20, 40)), 0, 50));
        thingBeans.add(new ThingBean("锻炼", "锻炼锻炼锻炼锻炼锻炼", new TimeAreaBean(new TimeBean(20, 40), new TimeBean(21, 40)), 0, 50));
        thingBeans.add(new ThingBean("回家", "回家回家回家回家", new TimeAreaBean(new TimeBean(21, 40), new TimeBean(21, 50)), 0, 50));
        thingBeans.add(new ThingBean("补充蛋白质", "补充蛋白质补充蛋白质补充蛋白质", new TimeAreaBean(new TimeBean(22, 0), new TimeBean(22, 30)), 0, 50));
        thingBeans.add(new ThingBean("弹钢琴", "弹钢琴弹钢琴", new TimeAreaBean(new TimeBean(22, 30), new TimeBean(23, 30)), 0, 50));
        thingBeans.add(new ThingBean("洗漱洗澡", "弹钢琴弹钢琴", new TimeAreaBean(new TimeBean(23, 30), new TimeBean(24, 0)), 0, 50));
        for (int i = 0; i < thingBeans.size(); i++) {
            thingBeans.get(i).setPos(i);
        }
        return thingBeans;
    }

    public void add(ThingBean thingBean) {
        thingBean.setPos(thingBeans.size());
        thingBeans.add(thingBean);
    }

    public int findNowThing() {
        for (int i = 0; i < thingBeans.size(); i++) {
            int s = thingBeans.get(i).getTimeArea().getStart().hour * 60 + thingBeans.get(i).getTimeArea().getStart().minute;
            int e = thingBeans.get(i).getTimeArea().getEnd().hour * 60 + thingBeans.get(i).getTimeArea().getEnd().minute;
            int n = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 + Calendar.getInstance().get(Calendar.MINUTE);
            if (n >= s && n <= e) {
                LogUtil.E(thingBeans.get(i).getPos() + "-----" + thingBeans.size() + "------" + ThingView.maxcount);
                return (thingBeans.get(i).getPos() + thingBeans.size() - (ThingView.maxcount - 1) / 2) % thingBeans.size();
            }
        }
        return -1;
    }

    public ArrayList<ThingBean> getThingBeans() {
        return thingBeans;
    }

    public void setThingBeans(ArrayList<ThingBean> thingBeans) {
        this.thingBeans = thingBeans;
    }
}
