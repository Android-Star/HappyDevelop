package com.example.wilsonhan.happydevelop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.wilsonhan.happydevelop.app.AppApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    private static String TAG = "CommonUtils";
    private static List<String> pushKeyList = new ArrayList<>();
    private static boolean shouldPlayBeep;

    private static Context context = AppApplication.getAppContext();


    /**
     * get App versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 播放声音
     *
     * @param activity
     * @return
     */
    public static MediaPlayer OpeanAudio(Activity activity, AssetFileDescriptor file) {
        android.media.AudioManager audioService = (android.media.AudioManager) activity
                .getSystemService(Context.AUDIO_SERVICE);
        if (audioService.getRingerMode() != android.media.AudioManager.RINGER_MODE_NORMAL) {
            shouldPlayBeep = false;
        }


        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 注册事件。当播放完毕一次后，重新指向流文件的开头，以准备下次播放。
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.seekTo(0);
            }
        });

//        AssetFileDescriptor file = activity.getResources().openRawResourceFd(R.raw.video);

        try {
            mediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
            file.close();
            mediaPlayer.setVolume(0.5f, 0.5f);
            mediaPlayer.prepare();
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            mediaPlayer = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        return mediaPlayer;
    }


}
