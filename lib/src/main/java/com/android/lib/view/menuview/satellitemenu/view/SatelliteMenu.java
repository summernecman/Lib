package com.android.lib.view.menuview.satellitemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.lib.R;
import com.android.lib.R2;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.AnimUtil;
import com.android.lib.view.menuview.satellitemenu.bean.SatelliteBean;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-11-07.
 */
public class SatelliteMenu extends RelativeLayout implements View.OnClickListener {

    public static final int STATE_RUN = 0;
    public static final int STATE_OPEN = 1;
    public static final int STATE_CLOSE = 2;
    public static int STATE = STATE_CLOSE;
    Context context;
    ImageView menuIv;
    RelativeLayout rootRL;
    int w;
    int h;
    ArrayList<View> itemViews = new ArrayList<>();
    ArrayList<SatelliteBean> satelliteBeen = new ArrayList<>();


    public SatelliteMenu(Context context) {
        super(context);
        init(context, null);
    }

    public SatelliteMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_satellite_menu, null);
        addView(relativeLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        menuIv = (ImageView) relativeLayout.findViewById(R.id.iv_menu);
        rootRL = (RelativeLayout) relativeLayout.findViewById(R.id.rl_root);
        menuIv.setOnClickListener(this);

        w = h = ValueConstant.DIMEN_1 * 200;
    }

    public void addItem(int[] drawable) {
        float degree = 90f / drawable.length + 1;
        for (int i = 0; i < drawable.length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_satellite_image, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_menu_item);
            imageView.setImageResource(drawable[i]);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = w - 45 * ValueConstant.DIMEN_1;
            params.topMargin = w - 45 * ValueConstant.DIMEN_1;
            rootRL.addView(view, params);
            view.setVisibility(View.GONE);
            itemViews.add(view);
            float rad = (float) Math.toRadians(degree * i);
            satelliteBeen.add(new SatelliteBean(0, 0, (int) Math.cos(rad), (int) Math.sin(rad)));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R2.id.iv_menu:
                switch (STATE) {
                    case STATE_CLOSE:

                        for (int i = 0; i < itemViews.size(); i++) {
                            final int finalI = i;
                            itemViews.get(finalI).setVisibility(View.VISIBLE);
                            AnimUtil.getInstance().animXY(v, satelliteBeen.get(i).getEndx(), 0, satelliteBeen.get(i).getEndy(), 0, new OnFinishListener() {
                                @Override
                                public void onFinish(Object o) {
                                    STATE = STATE_CLOSE;
                                    itemViews.get(finalI).setVisibility(View.GONE);
                                }
                            });
                        }
                        STATE = STATE_RUN;
                        break;
                    case STATE_OPEN:
                        for (int i = 0; i < itemViews.size(); i++) {
                            final int finalI = i;
                            itemViews.get(finalI).setVisibility(View.VISIBLE);
                            AnimUtil.getInstance().animXY(v, 0, satelliteBeen.get(i).getEndx(), 0, satelliteBeen.get(i).getEndy(), new OnFinishListener() {
                                @Override
                                public void onFinish(Object o) {
                                    STATE = STATE_OPEN;
                                }
                            });
                        }
                        STATE = STATE_RUN;
                        break;
                    case STATE_RUN:

                        break;
                }
                break;
        }
    }
}
