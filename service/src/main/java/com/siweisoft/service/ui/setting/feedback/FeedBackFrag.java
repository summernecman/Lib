package com.siweisoft.service.ui.setting.feedback;

//by summer on 17-08-28.

import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

public class FeedBackFrag extends BaseServerFrag<FeedBAckUIOpe, FeedBackDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "意见反馈", ""));
    }
}
