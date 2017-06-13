package com.summer.desktop.bean.uibean;

//by summer on 2017-06-13.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.ScreenUtil;

import butterknife.BindView;

public class DayMainItemUIBean extends BaseUIBean {


    @BindView(R.id.item)
    TextView item;

    int h = 0;

    public DayMainItemUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_day_main);
        if (h == 0) {
            h = (int) ((ScreenUtil.h - 50 * ValueConstant.DIMEN_1 - ScreenUtil.sbh - 118) / 60);
        }
        itemView.getLayoutParams().height = h;
        itemView.requestLayout();
    }

    public TextView getItem() {
        return item;
    }
}
