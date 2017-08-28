package com.siweisoft.service.videochat.chatutil;

//by summer on 2017-07-04.

import android.content.Context;
import android.view.SurfaceView;

import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.bairuitech.anychat.AnyChatCoreSDK;
import com.bairuitech.anychat.AnyChatDefine;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.VideoValue;
import com.siweisoft.service.ui.main.RoleInfo;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class ChatInit {

    private static ChatInit instance;
    private final int LOCALVIDEOAUTOROTATION = 1;    // 本地视频自动旋转控制
    AnyChatCoreSDK anyChatSDK;


    private String mStrIP = "demo.anychat.cn";


    public static ChatInit getInstance() {
        if (instance == null) {
            instance = new ChatInit();
        }
        return instance;
    }

    // 初始化SDK
    public void initSDK(Context context) {
        if (anyChatSDK == null) {
            anyChatSDK = AnyChatCoreSDK.getInstance(context);
            anyChatSDK.InitSDK(android.os.Build.VERSION.SDK_INT, 0);
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_AUTOROTATION, 1);
            VideoValue.ConfigEntity mConfigEntity = ConfigService.LoadConfig(context);
            mConfigEntity.mConfigMode = VideoValue.ConfigEntity.VIDEO_MODE_CUSTOMCONFIG;
            mConfigEntity.mResolutionWidth = 1080;
            mConfigEntity.mResolutionHeight = 720;
            mConfigEntity.mVideoBitrate = 400 * 1000;
            mConfigEntity.mVideoFps = 15;
            mConfigEntity.mVideoQuality = VideoValue.Config.mArrVideoQualityValue[2];
            mConfigEntity.mVideoPreset = VideoValue.Config.mArrVideoPresetValue[3];
            mConfigEntity.mVideoOverlay = 1;
            mConfigEntity.mVideoRotateMode = 0;
            mConfigEntity.mFixColorDeviation = 0;
            mConfigEntity.mVideoShowGPURender = 1;
            mConfigEntity.mVideoAutoRotation = 1;
            mConfigEntity.mEnableP2P = 0;
            mConfigEntity.mUseARMv6Lib = 0;
            mConfigEntity.mEnableAEC = 1;
            mConfigEntity.mUseHWCodec = 1;
            ConfigService.SaveConfig(context, mConfigEntity);
        }
        ConfigService.LoadConfig(context);
        ApplyVideoConfig(context);
    }

    // 根据配置文件配置视频参数
    public void ApplyVideoConfig(Context context) {
        VideoValue.ConfigEntity configEntity = ConfigService.LoadConfig(context);
        if (configEntity.mConfigMode == 1) {// 自定义视频参数配置
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_BITRATECTRL, configEntity.mVideoBitrate); // 设置本地视频编码的码率（如果码率为0，则表示使用质量优先模式）
            if (configEntity.mVideoBitrate == 0) {
                AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_QUALITYCTRL, configEntity.mVideoQuality);// 设置本地视频编码的质量
            }
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_FPSCTRL, configEntity.mVideoFps); // 设置本地视频编码的帧率
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_GOPCTRL, configEntity.mVideoFps * 4);// 设置本地视频编码的关键帧间隔
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_WIDTHCTRL, configEntity.mResolutionWidth);// 设置本地视频采集分辨率
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_HEIGHTCTRL, configEntity.mResolutionHeight);
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_PRESETCTRL, configEntity.mVideoPreset);// 设置视频编码预设参数（值越大，编码质量越高，占用CPU资源也会越高）
        }
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_APPLYPARAM, configEntity.mConfigMode);// 让视频参数生效
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_NETWORK_P2PPOLITIC, configEntity.mEnableP2P);// P2P设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_OVERLAY, configEntity.mVideoOverlay);// 本地视频Overlay模式设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_AUDIO_ECHOCTRL, configEntity.mEnableAEC);// 回音消除设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_CORESDK_USEHWCODEC, configEntity.mUseHWCodec); // 平台硬件编码设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_ROTATECTRL, configEntity.mVideoRotateMode);// 视频旋转模式设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_FIXCOLORDEVIA, configEntity.mFixColorDeviation);// 本地视频采集偏色修正设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_VIDEOSHOW_GPUDIRECTRENDER, configEntity.mVideoShowGPURender); // 视频GPU渲染设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_AUTOROTATION, configEntity.mVideoAutoRotation); // 本地视频自动旋转设置
    }

    public void doConnect(String ip, int port) {
        anyChatSDK.Connect(ip, port);
    }

    public void doLogin(String name) {
        /***
         * AnyChat支持多种用户身份验证方式，包括更安全的签名登录，
         * 详情请参考：http://bbs.anychat.cn/forum.php?mod=viewthread&tid=2211&highlight=%C7%A9%C3%FB
         */
        if (anyChatSDK == null) {
            return;
        }
        anyChatSDK.Login(name, "");
    }

    public void doLogin(String ip, int port, String name) {
        if (anyChatSDK == null) {
            return;
        }
        anyChatSDK.Connect(ip, port);
        anyChatSDK.Login(name, "");
    }

    public void doLoginOut() {
        if (anyChatSDK == null) {
            return;
        }
        anyChatSDK.Logout();
    }

    public void clear(Object o) {
        if (anyChatSDK == null) {
            return;
        }
        anyChatSDK.LeaveRoom(-1);
        anyChatSDK.Logout();
        anyChatSDK.removeEvent(o);
        anyChatSDK.Release();
        anyChatSDK = null;
    }


    public void enterRoom(int roomID, String pwd) {
        if (anyChatSDK != null) {
            anyChatSDK.EnterRoom(roomID, pwd);
        }
    }

    public void leaveRoom(int roomID) {
        if (anyChatSDK != null) {
            anyChatSDK.LeaveRoom(roomID);
        }
    }

    public ArrayList<UserBean> getUserList() {

        ArrayList<UserBean> users = new ArrayList<>();
        int[] userID = anyChatSDK.GetRoomOnlineUsers(VideoValue.URL.ROOMID);
//        UserBean userBean = new UserBean();
//        users.add(userBean);
//        userBean.setChatid(String.valueOf(myid));
//        userBean.setName(anyChatSDK.GetUserName(myid) + "(自己)");

        for (int index = 0; index < userID.length; ++index) {
            UserBean userBean = new UserBean();
            userBean.setName(anyChatSDK.GetUserName(userID[index]));
            userBean.setChatid(String.valueOf(userID[index]));
            users.add(userBean);
            LogUtil.E(userBean.toString());
        }
        return users;
    }

    public void openLocalCamera() {
        //打开本地视频, 第一个参数用-1 表示本地，也可以用本地的真实 userid
        getAnyChatSDK().UserCameraControl(-1, 1);
        //打开本地音频
        getAnyChatSDK().UserSpeakControl(-1, 1);
    }


    public void openLocalCamera(int userid) {
        //打开本地视频, 第一个参数用-1 表示本地，也可以用本地的真实 userid
        getAnyChatSDK().UserCameraControl(userid, 1);
        //打开本地音频
        getAnyChatSDK().UserSpeakControl(userid, 1);
    }


    public void loadRemoveVideo(SurfaceView mSurfaceRemote, int removeid) {
//        // mRemoteUserid 为通话目标对象的 userId;
//        int index = getAnyChatSDK().mVideoHelper.bindVideo(mSurfaceRemote.getHolder());
//        getAnyChatSDK().mVideoHelper.SetVideoUser(index, removeid);
        getAnyChatSDK().UserCameraControl(removeid, 1);
        getAnyChatSDK().UserSpeakControl(removeid, 1);
    }

    public void closeRemoveVideo(int removeid) {
        //关闭远程视频, mRemoteUserid 为通话目标的 userid
        getAnyChatSDK().UserCameraControl(removeid, 0);
        //关闭远程音频,
        getAnyChatSDK().UserSpeakControl(removeid, 0);
    }


    public void startRecordVideo() {
        int mdwFlags = 0;                    // 本地视频录制参数标致
        mdwFlags = AnyChatDefine.BRAC_RECORD_FLAGS_AUDIO
                + AnyChatDefine.BRAC_RECORD_FLAGS_VIDEO
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_LOCALCB
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_SERVER;
        anyChatSDK.StreamRecordCtrlEx(-1, 1, mdwFlags, 0, "开始录制");
    }

    public void startRecordVideo(UserBean userBean, VideoBean bean) {
        int mdwFlags = 0;                    // 本地视频录制参数标致
        mdwFlags = AnyChatDefine.BRAC_RECORD_FLAGS_AUDIO
                + AnyChatDefine.BRAC_RECORD_FLAGS_VIDEO
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_LOCALCB
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_SERVER;
        anyChatSDK.StreamRecordCtrlEx(Integer.parseInt(bean.getOtherid()), 1, mdwFlags, 0, GsonUtil.getInstance().toJson(bean));
    }

    public void startRecordVideo(UserBean userBean, RoleInfo roleInfo, VideoBean videoBean) {
        int mdwFlags = 0;                    // 本地视频录制参数标致
        mdwFlags = AnyChatDefine.BRAC_RECORD_FLAGS_AUDIO
                + AnyChatDefine.BRAC_RECORD_FLAGS_VIDEO
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_LOCALCB
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_SERVER;
        anyChatSDK.StreamRecordCtrlEx(Integer.parseInt(roleInfo.getUserID()), 1, mdwFlags, 0, roleInfo.getName());
    }

    public void stopRecordVideo(UserBean userBean) {
        int mdwFlags = 0;                    // 本地视频录制参数标致
        mdwFlags = AnyChatDefine.BRAC_RECORD_FLAGS_AUDIO
                + AnyChatDefine.BRAC_RECORD_FLAGS_VIDEO
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_LOCALCB
                + AnyChatDefine.ANYCHAT_RECORD_FLAGS_SERVER;
        anyChatSDK.StreamRecordCtrlEx(-1, 0, mdwFlags, 0,
                userBean.getName());
    }


    public AnyChatCoreSDK getAnyChatSDK() {
        return anyChatSDK;
    }

    public String getmStrIP() {
        return mStrIP;
    }

}
