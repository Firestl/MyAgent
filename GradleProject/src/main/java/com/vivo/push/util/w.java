package com.vivo.push.util;

import android.content.Context;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;

/* JADX INFO: compiled from: SharePreferenceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w extends b {
    public static w b;

    public static synchronized w b() {
        if (b == null) {
            b = new w();
        }
        return b;
    }

    public final synchronized void a(Context context) {
        if (this.f5630a == null) {
            this.f5630a = context;
            a(context, "com.vivo.push_preferences");
        }
    }

    public final byte[] c() {
        byte[] bArrC = c(b("com.vivo.push.secure_cache_iv", ""));
        return (bArrC == null || bArrC.length <= 0) ? new byte[]{34, 32, PublicSuffixDatabase.EXCEPTION_MARKER, 37, PublicSuffixDatabase.EXCEPTION_MARKER, 34, 32, PublicSuffixDatabase.EXCEPTION_MARKER, PublicSuffixDatabase.EXCEPTION_MARKER, PublicSuffixDatabase.EXCEPTION_MARKER, 34, 41, 35, 32, 32, 32} : bArrC;
    }

    public final byte[] d() {
        byte[] bArrC = c(b("com.vivo.push.secure_cache_key", ""));
        return (bArrC == null || bArrC.length <= 0) ? new byte[]{PublicSuffixDatabase.EXCEPTION_MARKER, 34, 35, 36, 37, 38, 39, 40, 41, 32, 38, 37, 36, 35, 34, PublicSuffixDatabase.EXCEPTION_MARKER} : bArrC;
    }

    public static byte[] c(String str) {
        int length;
        byte[] bArr = null;
        try {
            String[] strArrSplit = str.split(",");
            if (strArrSplit.length > 0) {
                bArr = new byte[strArrSplit.length];
                length = strArrSplit.length;
            } else {
                length = 0;
            }
            for (int i = 0; i < length; i++) {
                bArr[i] = Byte.parseByte(strArrSplit[i].trim());
            }
        } catch (Exception e2) {
            o.a("SharePreferenceManager", "getCodeBytes error:" + e2.getMessage());
        }
        return bArr;
    }
}
