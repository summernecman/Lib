package com.android.lib.view.switcmenu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.R;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-11-07.
 */
public class SwithMenuView1 extends LinearLayout implements View.OnClickListener {


    ArrayList<String> strings = new ArrayList<>();
    View lineView;
    ViewPager viewPager;
    ArrayList<TextView> textViews = new ArrayList<>();
    ViewPager.OnPageChangeListener listener;
    private Context context;
    private ArrayList<ItemSwitchMenu> itemSwitchMenus = new ArrayList<>();
    private OnClickListener onClickListener;

    public SwithMenuView1(Context context, ArrayList<String> strings) {
        super(context);
        this.context = context;
        this.strings = strings;
        init();
    }

    public SwithMenuView1(Context context, AttributeSet attrs, ArrayList<String> strings) {
        super(context, attrs);
        init();
    }

    private void init() {
        ViewGroup viewGroup1 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_swith_menu, null);
        addView(viewGroup1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        lineView = viewGroup1.findViewById(R.id.line);
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.menu_root);
        for (int i = 0; i < strings.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_swith_menu_txt, null);
            viewGroup.addView(view, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            TextView textView = (TextView) view.findViewById(R.id.text);
            textViews.add(textView);
            textView.setText(strings.get(i));
        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LayoutParams params = (LayoutParams) lineView.getLayoutParams();
        params.width = getWidth() / strings.size();
        lineView.setTag(params.width);
        lineView.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {

    }

    public void setViewPager(final ViewPager viewPager) {
        this.viewPager = viewPager;
        if (viewPager == null) {
            return;
        }
        if (listener != null) {
            viewPager.removeOnPageChangeListener(listener);
        }
        listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                lineView.setTranslationX(lineView.getWidth() * (position + positionOffset));
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < textViews.size(); i++) {
                    if (i == position) {
                        textViews.get(i).setTextColor(getResources().getColor(R.color.color_base));
                    } else {
                        textViews.get(i).setTextColor(getResources().getColor(R.color.black));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.addOnPageChangeListener(listener);

        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setTag(i);
            textViews.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = (int) v.getTag();
                    viewPager.setCurrentItem(p);
                }
            });
        }
    }
}
