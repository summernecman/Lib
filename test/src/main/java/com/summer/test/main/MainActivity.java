package com.summer.test.main;

//by summer on 2017-07-10.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.util.LogUtil;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.List;

public class MainActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AVObject test = new AVObject("test");
        test.put("txt", "txt");
        test.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                LogUtil.E(e);
            }
        });

        AVQuery<AVObject> query = new AVQuery<>("test");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (int i = 0; i < list.size(); i++) {
                    LogUtil.E(list.get(i).getString("txt"));
                }
            }
        });
    }
}
