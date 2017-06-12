package com.summer.lib.view.bottommenu;

//by summer on 2017-05-23.

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.summer.lib.R;

public class BottomItemView extends ViewPager {


    public BottomItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true, new MyPagerTransForm());
    }

    public static class MyPagerTransForm implements PageTransformer {

        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            if (position > -1 && position <= 1) {
                float y = (float) (0.5f + 0.5f * Math.sin((position + 0.1f) * Math.PI));
                view.setScaleX(y);
                view.setScaleY(y);
            }
        }
    }

    public static class MenuItemFrag extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.frag_menu, null);
        }
    }

    public static class MenuAdapter extends PagerAdapter implements OnClickListener {

        Context context;

        int[] images = new int[]{R.drawable.ic_launcher_round, R.drawable.ic_note};


        public MenuAdapter(Context context) {
            this.context = context;
        }


        @Override
        public int getCount() {
            return Integer.MAX_VALUE >> 2;
//            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.frag_menu, container, false);
            TextView textView = (TextView) view.findViewById(R.id.text);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_menu);
            imageView.setImageResource(images[position % 2]);
            textView.setText(position + "");
            container.addView(view);
            view.setOnClickListener(this);
            view.setTag(R.id.data, position);
            view.setTag(R.id.context, context);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public float getPageWidth(int position) {
            return 0.2f;
        }


        @Override
        public void onClick(View v) {
//            MessageEvent messageEvent = new MessageEvent();
//            messageEvent.position = (int) v.getTag(R.id.data);
//            EventBus.getDefault().post(messageEvent);
        }
    }


}
