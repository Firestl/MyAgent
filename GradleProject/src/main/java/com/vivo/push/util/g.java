package com.vivo.push.util;

import android.os.Looper;
import android.util.Log;

/* JADX INFO: compiled from: DebugUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    public static void a(String str) {
        if (o.a() && Looper.myLooper() == Looper.getMainLooper()) {
            Log.w("DebugUtil", "Operation: " + str + " in main thread!", new Throwable());
        }
    }
}
