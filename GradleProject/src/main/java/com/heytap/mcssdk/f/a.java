package com.heytap.mcssdk.f;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2576a = "com.nearme.mcs";

    public static String a() {
        byte[] bArrA = a(a(f2576a));
        return bArrA != null ? new String(bArrA, Charset.forName("UTF-8")) : "";
    }

    public static byte[] a(String str) {
        if (str != null) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new byte[0];
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length % 2 == 0 ? bArr.length : bArr.length - 1;
        for (int i = 0; i < length; i += 2) {
            byte b = bArr[i];
            bArr[i] = bArr[r3];
            bArr[i + 1] = b;
        }
        return bArr;
    }

    public static String b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return b.a(str, a());
            } catch (Exception e2) {
                c.e("desDecrypt-" + e2.getMessage());
            }
        }
        return "";
    }
}
