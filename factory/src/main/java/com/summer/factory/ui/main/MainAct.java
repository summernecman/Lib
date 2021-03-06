package com.summer.factory.ui.main;

//by summer on 2017-07-18.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.summer.factory.R;
import com.summer.factory.ui.activity.FrmBeginWorkAct;
import com.summer.factory.ui.activity.FrmCodeBindAct;
import com.summer.factory.ui.activity.FrmConfirmSoAct;
import com.summer.factory.ui.activity.FrmEndWorkAct;
import com.summer.factory.ui.activity.FrmMoveAct;
import com.summer.factory.ui.activity.FrmOtherInAct;
import com.summer.factory.ui.activity.FrmOtherOutAct;
import com.summer.factory.ui.activity.FrmPurReceiveAct;
import com.summer.factory.ui.activity.FrmSWSMoveAct;
import com.summer.factory.ui.activity.FrmSearchItem2Act;
import com.summer.factory.ui.activity.FrmSearchItemAct;
import com.summer.factory.ui.activity.FrmUpdateWorkAct;
import com.summer.factory.ui.activity.FrmWorkAct;
import com.summer.factory.ui.activity.FrmWorkSiteCheckAct;

public class MainAct extends BaseUIActivity<MainActUIOpe, MainActDAOpe> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initList(getP().getD().getTxts(), this);
    }

    @Override
    public void onClick(View v) {
        int positon = (int) v.getTag(R.id.position);
        switch (positon) {
            case 0:
                startActivity(new Intent(this, FrmPurReceiveAct.class));
                break;
            case 1:
                startActivity(new Intent(this, FrmWorkAct.class));
                break;
            case 2:
                startActivity(new Intent(this, FrmOtherInAct.class));
                break;
            case 3:
                startActivity(new Intent(this, FrmOtherOutAct.class));
                break;
            case 4:
                startActivity(new Intent(this, FrmMoveAct.class));
                break;
            case 5:
                startActivity(new Intent(this, FrmSearchItemAct.class));
                break;
            case 6:
                startActivity(new Intent(this, FrmSearchItem2Act.class));
                break;
            case 7:
                startActivity(new Intent(this, FrmBeginWorkAct.class));
                break;
            case 8:
                startActivity(new Intent(this, FrmUpdateWorkAct.class));
                break;
            case 9:
                startActivity(new Intent(this, FrmEndWorkAct.class));
                break;

            case 29:
                startActivity(new Intent(this, FrmConfirmSoAct.class));
                break;
            case 30:
                startActivity(new Intent(this, FrmWorkSiteCheckAct.class));
                break;
            case 31:
                startActivity(new Intent(this, FrmSWSMoveAct.class));
                break;
            case 32:
                startActivity(new Intent(this, FrmCodeBindAct.class));
                break;

        }
    }
}
