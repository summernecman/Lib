//package com.android.lib.view.video;
//
////by summer on 17-10-23.
//
//import android.content.Context;
//import android.graphics.Matrix;
//import android.util.AttributeSet;
//import android.view.ViewGroup;
//
//import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
//import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
//
//public class MyVideoPlayer extends StandardGSYVideoPlayer {
//
//
//    public MyVideoPlayer(Context context, Boolean fullFlag) {
//        super(context, fullFlag);
//    }
//
//    public MyVideoPlayer(Context context) {
//        super(context);
//    }
//
//    public MyVideoPlayer(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public void rotate() {
//        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
//        changeTextureViewShowType();
//        Matrix transform = new Matrix();
//        transform.setScale(1, -1, 0, mTextureView.getHeight() / 2);
//        mTextureView.setTransform(transform);
//        mTextureView.invalidate();
//    }
//
//    protected void changeTextureViewShowType() {
//        int params = getTextureParams();
//        ViewGroup.LayoutParams layoutParams = mTextureView.getLayoutParams();
//        layoutParams.width = params;
//        layoutParams.height = params;
//        mTextureView.setLayoutParams(layoutParams);
//    }
//
//}
