// ISdkService.aidl
package com.unisound.sdk.service;
import com.unisound.sdk.service.ISdkCallBack;
// Declare any non-default types here with import statements

interface ISdkService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void  setAsrOption(int key, String value);
    void  setAsrOption2(int key, boolean value);
    boolean enterAsr(boolean showEmotion);
    boolean cancelEngine();
    boolean enterWakeUp();
    void  exitMultiRoundDialogue();
    void  setTtsOption(int key, String value);
    boolean playTts(String tts);
    void setLedState(int state, boolean on);
    boolean cancelTts();
    int getTtsModelId();
    int getAudioRecorderCount();
    void  switchTts(int ttsModelId);
    void  interruptOthers(ISdkCallBack cb,String interrupt);
    void  resumeOthers(ISdkCallBack cb);
    void  reset(ISdkCallBack cb);
    void  registerCallback(ISdkCallBack cb);
    void  unRegisterCallback(ISdkCallBack cb);
    void  changeWakeUpStatus(boolean status);
    String  setOption(int type, int key, String value);
}
