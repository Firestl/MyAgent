package com.unicom.xiaowo.account.shield.e;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5549a = true;

    public static void a(String str) {
        if (f5549a) {
            Log.i("uniaccount", "5.2.3AR002B0427 " + str);
        }
    }

    public static void a(boolean z) {
        f5549a = z;
    }

    public static void b(String str) {
        Log.e("uniaccount", "5.2.3AR002B0427 " + str);
    }
}
