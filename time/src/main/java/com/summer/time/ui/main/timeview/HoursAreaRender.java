package com.summer.time.ui.main.timeview;

//by summer on 2017-11-21.

import android.content.Context;
import android.graphics.Canvas;

import com.android.lib.util.data.DateFormatUtil;
import com.summer.time.R;

public class HoursAreaRender extends HoursBackRender {


    public HoursAreaRender(Context context) {
        super(context);
        color = context.getResources().getColor(R.color.color_brown_300);
        paint.setColor(color);
        radious = 25;
    }

    @Override
    public void onTimeDraw(Canvas canvas) {
        canvas.save();
        paint.setColor(color);
        initPath();
        canvas.clipPath(path);
        canvas.drawOval(getRectF(), paint);
        canvas.restore();
    }


    protected void initPath() {
        path.reset();
        path.moveTo(0, sh);
        path.lineTo(sw / 2, sh);
        path.lineTo(sw / 2, sh / 2);
        path.lineTo((float) getInnX(), (float) getInnY());
        path.lineTo((float) getX(), (float) getY());
        int level = 0;
        switch (DateFormatUtil.getHours() / 6) {
            case 0:
                level = 4;
                break;
            default:
                level = DateFormatUtil.getHours() / 6;
                break;
        }
        if (level > 3) {
            path.lineTo(sw, sh);
        }
        if (level > 2) {
            path.lineTo(sw, 0);
        }
        if (level > 1) {
            path.lineTo(0, 0);
        }
        if (level > 0) {
            path.lineTo(0, sh);
        }
        path.close();
    }

    public double getInnX() {
        return (getA() - mw * radious) * Math.cos(Math.toRadians(getDegree(getPos()))) + getXL();
    }

    public double getInnY() {
        return (getB() - mw * radious) * Math.sin(Math.toRadians(getDegree(getPos()))) + getYL();
    }

    public double getInnX(float degree) {
        return (getA() - mw * radious) * Math.cos(Math.toRadians(degree)) + getXL();
    }

    public double getInnY(float degree) {
        return (getB() - mw * radious) * Math.sin(Math.toRadians(degree)) + getYL();
    }


}
