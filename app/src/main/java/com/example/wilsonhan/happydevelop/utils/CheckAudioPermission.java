package com.example.wilsonhan.happydevelop.utils;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class CheckAudioPermission {

    private static final String TAG = "CheckPermissionUtils";
    private static CheckAudioPermission checkAudioPermission = new CheckAudioPermission();
    static final int SAMPLE_RATE_IN_HZ = 44100;
    static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(
            SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT,
            AudioFormat.ENCODING_PCM_16BIT);
    private AudioRecord mAudioRecord;
    boolean isGetVoiceRun;
    private Object mLock;
    private int count=0;
    private boolean isHasPermission;
    private CheckAudioPermission() {
        mLock = new Object();
    }


    public static CheckAudioPermission getinstance() {
        if (checkAudioPermission == null) {
            checkAudioPermission = new CheckAudioPermission();
        }
        return checkAudioPermission;
    }


    /**
     *获取app的录音权限是否打开
     *android 6.0version以上 传统方法不适合 以此兼容
     * @param context
     */
    public static boolean isHasAudioRecordPermission(Context context) {
// 音频获取源
        int audioSource = MediaRecorder.AudioSource.MIC;
        // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
        int sampleRateInHz = 44100;
        // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
        int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        // 缓冲区字节大小
        int bufferSizeInBytes = 0;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        AudioRecord audioRecord =  new AudioRecord(audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes);
        //开始录制音频
        try{
            // 防止某些手机崩溃，例如联想
            audioRecord.startRecording();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        /**
         * 根据开始录音判断是否有录音权限
         */
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            return false;
        }
        audioRecord.stop();
        audioRecord.release();
        audioRecord = null;
        return true;

    }

}
