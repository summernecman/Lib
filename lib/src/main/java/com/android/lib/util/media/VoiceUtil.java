package com.android.lib.util.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import com.android.lib.base.interf.OnLoadingInterf;
import com.android.lib.util.ToastUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by ${viwmox} on 2016-11-03.
 */
public class VoiceUtil {

    private static VoiceUtil instance;
    MediaPlayer mp;

    public static VoiceUtil getInstance() {
        if (instance == null) {
            instance = new VoiceUtil();
        }
        return instance;
    }

    public void play(String path, final OnLoadingInterf linstener) {
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.pause();
            }
            mp = null;
        }
        mp = new MediaPlayer();
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            linstener.onStarLoading(mp);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    linstener.onStopLoading(mp);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }

    public MediaRecorder startRecording(Context context, File recordFile) {
        MediaRecorder mediaRecorder = new MediaRecorder();
        // 判断，若当前文件已存在，则删除
        if (recordFile.exists()) {
            recordFile.delete();
        }
        try {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            ToastUtil.getInstance().showLong(context, "请打开录音权限");
            return null;
        }
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(recordFile.getAbsolutePath());

        try {
            // 准备好开始录音
            mediaRecorder.prepare();

            mediaRecorder.start();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mediaRecorder;
    }


    public void stopRecording(MediaRecorder mediaRecorder, File recordFile) {
        if (recordFile != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

}
