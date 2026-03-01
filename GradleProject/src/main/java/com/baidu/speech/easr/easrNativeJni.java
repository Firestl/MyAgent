package com.baidu.speech.easr;

/* JADX INFO: loaded from: classes.dex */
public class easrNativeJni {
    static {
        try {
            synchronized (easrNativeJni.class) {
                System.loadLibrary("bdEASRAndroid");
            }
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static native synchronized int AECExit();

    public static native synchronized int AECInit();

    public static native synchronized int AECProcess(short[] sArr, short[] sArr2, short[] sArr3, int i);

    public static native synchronized int AECReset();

    public static native synchronized int SetLogLevel(int i);
}
