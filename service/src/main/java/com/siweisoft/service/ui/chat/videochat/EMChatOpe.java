package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-20.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.system.SystemUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.siweisoft.service.ui.Constant.Value;

public class EMChatOpe extends BaseDAOpe {


    public EMChatOpe(Context context) {
        super(context);
    }


    public void initEM(Context context) {
        int pid = android.os.Process.myPid();
        String processAppName = SystemUtil.getAppName(context, pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            LogUtil.E("enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        EMOptions options = new EMOptions();

        options.setAcceptInvitationAlways(true);// 默认添加好友时，是不需要验证的，改成需要验证
        //options.setAutoLogin(false);
        EMClient.getInstance().init(context, options);//初始化
        EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(true);
        EMClient.getInstance().setDebugMode(true);//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().callManager().getCallOptions().setVideoResolution(360,640);
//        EMClient.getInstance().callManager().getCallOptions().setMinVideoKbps(400);
//        EMClient.getInstance().callManager().getCallOptions().setMaxVideoKbps(1000);
        EMClient.getInstance().callManager().getVideoCallHelper().setPreferMovFormatEnable(true);
    }

    public static void sendCmdMsg(String to, String file) {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        //支持单聊和群聊，默认单聊，如果是群聊添加下面这行
        cmdMsg.setChatType(EMMessage.ChatType.Chat);
        String action = file;//action可以自定义
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        cmdMsg.setFrom(Value.getUserInfo().getPhone());
        cmdMsg.setTo(to);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }
}
