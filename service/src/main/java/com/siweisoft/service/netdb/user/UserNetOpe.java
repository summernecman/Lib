package com.siweisoft.service.netdb.user;

//by summer on 2017-07-12.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.netadapter.UINetAdapter;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;

import java.util.ArrayList;

public class UserNetOpe extends BaseDAOpe implements UserI {

    public UserNetOpe(Context context) {
        super(context);
    }

    @Override
    public void registed(String username, OnFinishListener onFinishListener) {

    }

    @Override
    public void regist(final UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/registEMAccount", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (!o.isException()) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(b);
                } else {
                    onFinishListener.onFinish(o.getErrorMessage());
                }
            }
        });
    }

    @Override
    public void resetPwd(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/resetPwd", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (!o.isException()) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(b);
                } else {
                    onFinishListener.onFinish(o.getErrorMessage());
                }
            }
        });
    }

    @Override
    public void login(final UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/login", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                //UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(o);
            }
        });
    }

    @Override
    public void getUserList(final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(new UserBean()));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUserList", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUnTypeUserList(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUnTypeUserList", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUserListWithOutMe(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUserListWithOutMe", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }


    @Override
    public void getLoginInfo(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/getLoginInfo", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void loginOut(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/server/loginOut", baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void setHeadUrl(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/setHeadurl", baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void setName(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/setUserName", baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUserCallInfo(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getusercallinfo", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                VideoTimeBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), VideoTimeBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUserInfoByPhone(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUserInfoByPhone", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUserInfoById(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUserInfoById", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getUsersInfoByPhone(ArrayList<UserBean> userBeen, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBeen));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getUsersInfoByPhone", baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> userBeen, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBeen));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getArrayUsersInfoByPhone", baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<ArrayList<UserBean>> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<ArrayList<UserBean>>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getOtherUsersInfoByPhone(AllUserBean allUserBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(allUserBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/getOtherUsersInfoByPhone", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                }.getType());
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void updateUnit(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.getInstance(context).doHttpRequsetWithSession(context, "/user/updateUnit", baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }
}
