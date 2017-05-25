package com.siweisoft.app.home;

//by summer on 2017-03-31.

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.siweisoft.app.base.AppFrag;
import com.summer.lib.base.ope.BaseOpes;

public class HomeFrag extends AppFrag<HomeUIOpe,HomeDAOpe> implements View.OnDragListener{

    @Override
    public BaseOpes<HomeUIOpe, HomeDAOpe> createOpes() {
        return new BaseOpes<>(new HomeUIOpe(activity),new HomeDAOpe(activity));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getOpes().getDaOpe().getApps(new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                getOpes().getUiOpe().setApps((ArrayList<AppDBBean>) o);
//            }
//        });
        getOpes().getUiOpe().getUiBean().getTvDrag().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getOpes().getUiOpe().getUiBean().getTvDrag().startDrag(null,new View.DragShadowBuilder(),getOpes().getUiOpe().getUiBean().getTvDrag().getDrawable(),0);
                return true;
            }
        });
        //getOpes().getUiOpe().getUiBean().getTvDrag().setOnDragListener(this);
    }

    String msg="main";
    int x_cord = 0;
    int y_cord = 0;
    @Override
    public boolean onDrag(View v, DragEvent event) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
        switch(event.getAction())
        {
            case DragEvent.ACTION_DRAG_STARTED:
                layoutParams = (RelativeLayout.LayoutParams)
                        v.getLayoutParams();
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DRAG_EXITED :
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DRAG_LOCATION  :
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DRAG_ENDED   :
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;
            case DragEvent.ACTION_DROP:
                Log.d(msg, "ACTION_DROP event");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                layoutParams.leftMargin = x_cord;
                layoutParams.topMargin = y_cord;
                v.setLayoutParams(layoutParams);
                break;
            default: break;
        }
        return true;
    }
}
