package com.siweisoft.service.ui.video.seach;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.NullUtil;

import java.util.List;

public class SeachDAOpe extends BaseDAOpe {

    private List list;

    private SeachBean seachBean;


    public SeachDAOpe(Context context) {
        super(context);
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }


    public boolean isClear(SeachBean seachBean) {
        boolean is = true;
        for (int i = 0; i < seachBean.getData().size(); i++) {
            if (seachBean.getData().get(i).isSelect()) {
                is = false;
            }
        }
        if (!NullUtil.isStrEmpty(seachBean.getTxt())) {
            is = false;
        }
        return is;
    }

    public SeachBean getSeachBean() {
        return seachBean;
    }

    public void setSeachBean(SeachBean seachBean) {
        this.seachBean = seachBean;
    }
}
