package com.siweisoft.service.ui.user.userheadname;

//by summer on 17-08-30.

import android.content.Intent;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.FileBean;
import com.android.lib.bean.FilesBean;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.login.UserBean;
import com.siweisoft.service.ui.user.rename.RenameFrag;

import java.io.File;
import java.util.ArrayList;

import butterknife.OnClick;

public class UserHeadNameFrag extends BaseUIFrag<UserHeadNameUIOpe, UserHeadNameDAOpe> {


    @Override
    public void doThing() {
        getP().getU().initInfo();
        getP().getU().initTitle();
    }

    @OnClick({R.id.ll_head, R.id.ll_name, R.id.ftv_back, R.id.ftv_title, R.id.ftv_right})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.ll_head:
                IntentUtil.getInstance().photoShowFromphone(fragment, ValueConstant.CODE_REQUSET3);
                break;
            case R.id.ll_name:
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, new RenameFrag());
                break;
            case R.id.ftv_right:
                break;
            case R.id.ftv_title:

                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        FilesBean filesBean = new FilesBean();
        ArrayList<FileBean> fileBeen = new ArrayList<>();
        fileBeen.add(new FileBean(new File(StringUtil.getInstance().getPath(activity, data.getData()))));
        filesBean.setData(fileBeen);

        NetWork.getInstance(activity).doHttpRequsetWithFile(activity, "/user/addheadurl", filesBean, new OnNetWorkReqAdapter(activity) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {
                LogUtil.E(o);
                ArrayList<String> files = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<String>>() {
                }.getType());
                UserBean userBean = new UserBean();
                userBean.setHeadurl(files.get(0));

                getP().getD().setHead(userBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        UserBean userBean = (UserBean) o;
                        Value.userBean.setHeadurl(userBean.getHeadurl());
                        getP().getU().initInfo();
                    }
                });
            }
        });


    }
}
