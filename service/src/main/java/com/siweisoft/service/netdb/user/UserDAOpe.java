package com.siweisoft.service.netdb.user;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;
import com.siweisoft.service.ui.user.login.UserInfo;

public class UserDAOpe extends BaseDAOpe implements UserI {

    public static final String user = "user";

    public UserDAOpe(Context context) {
        super(context);
    }

    @Override
    public void registed(String username, final OnFinishListener onFinishListener) {
        AVQuery<AVObject> query = new AVQuery<>(user);
        query.whereEqualTo("name", username);
        query.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (avObject == null) {
                    onFinishListener.onFinish(false);
                } else {
                    onFinishListener.onFinish(true);
                }
            }
        });
    }

    @Override
    public void regist(UserInfo userInfo, final OnFinishListener onFinishListener) {
        AVObject user = new AVObject(UserDAOpe.user);
        user.put("username", userInfo.name.get());
        user.put("pwd", userInfo.pwd.get());
        user.put("type", userInfo.type.get());
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                onFinishListener.onFinish(e == null ? true : false);
            }
        });
    }

    @Override
    public void login(final UserInfo userInfo, final OnFinishListener onFinishListener) {
        AVQuery<AVObject> query = new AVQuery<>(user);
        query.whereEqualTo("username", userInfo.name.get());
        query.whereEqualTo("pwd", userInfo.pwd.get());
        query.whereEqualTo("type", userInfo.type.get());
        query.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (avObject != null) {
                    userInfo.type.set(avObject.getBoolean("type"));
                    onFinishListener.onFinish(true);
                } else {
                    onFinishListener.onFinish(false);
                }
            }
        });
    }

    @Override
    public void logout(UserInfo userInfo, OnFinishListener onFinishListener) {

    }

    @Override
    public void getUserInfo(String name, final OnFinishListener onFinishListener) {
        AVQuery<AVObject> query = new AVQuery<>(user);
        query.whereEqualTo("username", name);
        query.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                UserInfo userInfo = new UserInfo();
                userInfo.type.set(avObject.getBoolean("type"));
                userInfo.name.set(avObject.getString("username"));
                userInfo.pwd.set(avObject.getString("pwd"));
                onFinishListener.onFinish(userInfo);
            }
        });
    }

}
