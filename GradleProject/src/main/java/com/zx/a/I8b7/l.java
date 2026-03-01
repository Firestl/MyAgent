package com.zx.a.I8b7;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f6238a = true;

    public static void a(String str) {
        if (f6238a) {
            StringBuilder sbA = m2.a("--- ");
            sbA.append(str == null ? com.igexin.push.core.b.m : str);
            sbA.append(" ---");
            Log.d("zx-DebugMode", sbA.toString());
        }
        if (str == null) {
            str = com.igexin.push.core.b.m;
        }
        y1.a(str);
    }

    public static void b(String str) {
        if (f6238a) {
            StringBuilder sbA = m2.a("--- ");
            if (str == null) {
                str = com.igexin.push.core.b.m;
            }
            sbA.append(str);
            sbA.append(" ---");
            Log.e("zx-DebugMode", sbA.toString());
        }
    }
}
