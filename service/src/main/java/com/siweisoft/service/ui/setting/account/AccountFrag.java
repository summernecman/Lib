package com.siweisoft.service.ui.setting.account;

//by summer on 17-08-28.

import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

public class AccountFrag extends BaseServerFrag<AccountUIOpe, AccountDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "账户安全", ""));
    }
}
