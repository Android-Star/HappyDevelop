// ISdkService.aidl
package com.unisound.sdk.service;
// Declare any non-default types here with import statements

interface ISdkCallBack {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    int acceptNlu(String service, String code, String result);
    void dealNlu(String service, String code, String result);
    boolean preDealCmdWakeUpResult(String wakeUpResult, float score);
    void dealCmdWakeUpResult(String wakeUpResult, float score);
    boolean needInterruptWithCmdWakeUp();
    void engineStateChange(int state, String value);
    int dealEvent(int event, String result);
    boolean isAlive();
    void doResume();
    void dealInterrupt(String serviceName, String interruptType, String extra);
    void broadState(int state);
}
