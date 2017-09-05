package com.siweisoft.service.ui.setting.aboutus;

//by summer on 17-08-28.

import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

public class AboutUsFrag extends BaseServerFrag<AboutUsUIOpe, AboutUsDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "关于我们", ""));
    }
}
