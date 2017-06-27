package com.android.lib.view.switcmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.R2;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ${viwmox} on 2016-07-29.
 */
public class ItemSwitchMenu extends LinearLayout {

    @BindView(R2.id.tv_title)
    TextView titleTv;

    @BindView(R2.id.swith_line)
    View lineView;

    private Context context;

    public ItemSwitchMenu(Context context, AttributeSet attrs, String title) {
        super(context, attrs);
        init(context, attrs, title);
    }

    private void init(Context context, AttributeSet attrs, String title) {
        this.context = context;
        addView(LayoutInflater.from(context).inflate(R.layout.item_swith_menu_item, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this, this);
        titleTv.setText(title);

    }


    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            getTitleTv().setTextColor(context.getResources().getColor(R.color.red));
            getLineView().setVisibility(View.VISIBLE);
        } else {
            getTitleTv().setTextColor(context.getResources().getColor(R.color.black));
            getLineView().setVisibility(View.INVISIBLE);
        }
    }

    public View getLineView() {
        return lineView;
    }

    public TextView getTitleTv() {
        return titleTv;
    }
}
