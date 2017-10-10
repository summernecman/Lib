package com.siweisoft.service.netdb.user;

//by summer on 2017-07-10.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.bean.AllUserBean;

import java.util.ArrayList;

public interface UserI {

    public void registed(String username, OnFinishListener onFinishListener);

    public void regist(UserBean userBean, OnFinishListener onFinishListener);

    public void resetPwd(UserBean userBean, OnFinishListener onFinishListener);

    public void login(UserBean userBean, OnFinishListener onFinishListener);

    public void getUserList(OnFinishListener onFinishListener);

    public void getUnTypeUserList(UserBean userBean, OnFinishListener onFinishListener);

    public void getUserListWithOutMe(UserBean userBean, OnFinishListener onFinishListener);


    public void getLoginInfo(UserBean userBean, OnFinishListener onFinishListener);

    public void loginOut(UserBean userBean, OnFinishListener onFinishListener);

    public void setHeadUrl(UserBean userBean, OnFinishListener onFinishListener);

    public void setName(UserBean userBean, OnFinishListener onFinishListener);

    public void getUserCallInfo(UserBean userBean, OnFinishListener onFinishListener);

    public void getUserInfoByPhone(UserBean userBean, OnFinishListener onFinishListener);

    public void getUsersInfoByPhone(ArrayList<UserBean> userBeen, OnFinishListener onFinishListener);

    public void getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> userBeen, OnFinishListener onFinishListener);

    public void getOtherUsersInfoByPhone(AllUserBean allUserBean, OnFinishListener onFinishListener);

    public void updateUnit(UserBean userBean, OnFinishListener onFinishListener);

}
