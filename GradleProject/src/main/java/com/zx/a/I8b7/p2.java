package com.zx.a.I8b7;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.zx.module.annotation.Java2C;

/* JADX INFO: loaded from: classes2.dex */
public class p2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f6264a = Integer.MIN_VALUE;
    public static Object b;

    @Java2C.Method2C
    public static native int a();

    @Java2C.Method2C
    public static native PackageInfo a(String str, int i) throws PackageManager.NameNotFoundException;

    @Java2C.Method2C
    public static native PackageInfo a(String str, int i, int i2);

    @Java2C.Method2C
    public static native PackageInfo b(String str, int i);
}
