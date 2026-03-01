package com.sangfor.atrust.JNIBridge;

import android.content.Context;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class JniTool {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f3869a;

    public static String a() {
        return f3869a.getPackageName();
    }

    public static native void native_onNameserverChanged();

    public static native void native_onNetworkChanged(int i);
}
