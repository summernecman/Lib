package com.siweisoft.service.netdb.collection;

//by summer on 17-09-04.

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.netdb.user.UserBean;

public interface CollectionI {

    public void getCollectionVideosByUserId(UserBean userBean, OnFinishListener onFinishListener);

    public void getCollectionVideosByUserIdWithLimit(UserBean userBean, OnFinishListener onFinishListener);

    public void isCollectedByVideoIdAndUserId(CollectionBean collectionBean, OnFinishListener onFinishListener);

    public void getCollectionNumByUserId(UserBean userBean, OnFinishListener onFinishListener);

    public void collect(CollectionBean collectionBean, OnFinishListener onFinishListener);

    public void disCollect(CollectionBean collectionBean, OnFinishListener onFinishListener);
}
