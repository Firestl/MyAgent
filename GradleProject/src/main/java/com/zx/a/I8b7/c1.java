package com.zx.a.I8b7;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c1 {
    public static c1 a(m0 m0Var, String str) {
        Charset.forName("UTF-8");
        if (m0Var != null && m0Var.a() == null) {
            Charset.forName("UTF-8");
            m0Var = m0.b(m0Var + "; charset=utf-8");
        }
        return a(m0Var, str.getBytes(StandardCharsets.UTF_8));
    }

    public static c1 a(m0 m0Var, byte[] bArr) {
        int length = bArr.length;
        long length2 = bArr.length;
        long j = 0;
        long j2 = length;
        if ((j | j2) >= 0 && j <= length2 && length2 - j >= j2) {
            return new b1(m0Var, length, bArr, 0);
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
