package com.summer.factory.ui.main;

//by summer on 2017-07-18.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.summer.factory.R;
import com.summer.factory.ui.activity.frmmove.FrmMoveAct;
import com.summer.factory.ui.activity.frmotherin.FrmOtherInAct;
import com.summer.factory.ui.activity.frmotherout.FrmOtherOutAct;
import com.summer.factory.ui.activity.frmpurreceive.FrmPurReceiveAct;
import com.summer.factory.ui.activity.frmwork.FrmWorkAct;

public class MainAct extends BaseUIActivity<MainActUIOpe, MainActDAOpe> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpes().getUi().initList(getOpes().getDa().getTxts(), this);
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
        }
    }
}
