package com.zx.a.I8b7;

import android.content.pm.PackageManager;
import android.content.pm.Signature;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    public static Signature[] a(String str) {
        try {
            return p2.a(str, 64).signatures;
        } catch (PackageManager.NameNotFoundException e2) {
            y1.a(e2);
            return null;
        }
    }
}
