package com.siweisoft.service.ui.Constant;

//by summer on 2017-07-06.

public class VideoValue {

    public static class URL {
        public static final String IP = "115.159.82.33";       //192.168.1.207      //180.168.218.122               //ip地址
        public static final int PROT = 8906;                                                               //端口号
        public static final int ROOMID = 1;                                                             //默认房间号

        public static final String URLROCORD = IP + ":8079/record";
    }

    public static class ConfigEntity {
        public static final int VIDEO_MODE_SERVERCONFIG = 0;                                        // 服务器视频参数配置
        public static final int VIDEO_MODE_CUSTOMCONFIG = 1;                                        // 自定义视频参数配置

        public static final int VIDEO_QUALITY_NORMAL = 2;                                            // 普通视频质量
        public static final int VIDEO_QUALITY_GOOD = 3;                                                // 中等视频质量
        public static final int VIDEO_QUALITY_BEST = 4;                                                // 较好视频质量

        public int mConfigMode = VIDEO_MODE_SERVERCONFIG;
        public int mResolutionWidth = 0;
        public int mResolutionHeight = 0;

        public int mVideoBitrate = 150 * 1000;                                                        // 本地视频码率
        public int mVideoFps = 10;                                                                    // 本地视频帧率
        public int mVideoQuality = VIDEO_QUALITY_GOOD;
        public int mVideoPreset = 1;
        public int mVideoOverlay = 1;                                                                // 本地视频是否采用Overlay模式
        public int mVideoRotateMode = 0;                                                            // 本地视频旋转模式
        public int mFixColorDeviation = 0;                                                            // 修正本地视频采集偏色：0 关闭(默认）， 1 开启
        public int mVideoShowGPURender = 0;                                                            // 视频数据通过GPU直接渲染：0  关闭(默认)， 1 开启
        public int mVideoAutoRotation = 1;                                                            // 本地视频自动旋转控制（参数为int型， 0表示关闭， 1 开启[默认]，视频旋转时需要参考本地视频设备方向参数）

        public int mEnableP2P = 1;
        public int mUseARMv6Lib = 0;                                                                // 是否强制使用ARMv6指令集，默认是内核自动判断
        public int mEnableAEC = 1;                                                                    // 是否使用回音消除功能
        public int mUseHWCodec = 0;                                                                    // 是否使用平台内置硬件编解码器
    }

    public static class Config {
        public static final String[] mArrVideoSizeStr = {"176 x 144", "320 x 240（默认）", "352 x 288", "640 x 480", "720 x 480", "1280 x 720"};
        public static final int[] mArrVideoWidthValue = {176, 320, 352, 640, 720, 1280};
        public static final int[] mArrVideoHeightValue = {144, 240, 288, 480, 480, 720};

        public static final String[] mArrVideoBitrateStr = {"质量优先模式", "60kbps（默认）", "80kbps", "100kbps", "150kbps", "200kbps", "300kbps", "500kbps", "800kbps", "1Mbps"};
        public static final int[] mArrVideoBitrateValue = {0, 60 * 1000, 80 * 1000, 100 * 1000, 150 * 1000, 200 * 1000, 300 * 1000, 500 * 1000, 800 * 1000, 1000 * 1000};

        public static final String[] mArrVideofpsStr = {"2 FPS", "4 FPS", "6 FPS", "8 FPS", "10FPS（默认）", "15FPS", "20FPS", "25FPS"};
        public static final int[] mArrVideofpsValue = {2, 4, 6, 8, 10, 15, 20, 25};

        public static final String[] mArrVideoQualityStr = {"普通视频质量", "中等视频质量（默认）", "较好视频质量"};
        public static final int[] mArrVideoQualityValue = {2, 3, 4};

        public static final String[] mArrVideoPresetStr = {"最高效率，较低质量", "较高效率，较低质量", "性能均衡（默认）", "较高质量，较低效率", "最高质量，较低效率"};
        public static final int[] mArrVideoPresetValue = {1, 2, 3, 4, 5};
    }
}
