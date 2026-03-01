package com.loc;

import java.util.List;

/* JADX INFO: compiled from: UploadBufferBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bw extends bt {
    public static bw b = new bw();

    public bw() {
        super(5120);
    }

    public static String a(String str) {
        return str == null ? "" : str;
    }

    public static bw b() {
        return b;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2, List<? extends ca> list) {
        if (list == null) {
            return null;
        }
        try {
            int size = list.size();
            if (size <= 0 || bArr == null) {
                return null;
            }
            a();
            int iA = cd.a((er) this.f3692a, bArr);
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ca caVar = list.get(i);
                iArr[i] = ci.a(this.f3692a, (byte) caVar.a(), ci.a(this.f3692a, caVar.b()));
            }
            this.f3692a.c(cd.a(this.f3692a, iA, bArr2 != null ? cd.b(this.f3692a, bArr2) : 0, cd.a(this.f3692a, iArr)));
            return this.f3692a.c();
        } catch (Throwable th) {
            dg.a(th);
            return null;
        }
    }

    public final byte[] c() {
        super.a();
        try {
            this.f3692a.c(df.a(this.f3692a, de.a(), this.f3692a.a(de.f()), this.f3692a.a(de.c()), (byte) de.m(), this.f3692a.a(de.i()), this.f3692a.a(de.h()), this.f3692a.a(a(de.g())), this.f3692a.a(a(de.j())), dd.a(de.n()), this.f3692a.a(de.l()), this.f3692a.a(de.k()), this.f3692a.a(de.d()), this.f3692a.a(de.e())));
            return this.f3692a.c();
        } catch (Exception e2) {
            dg.a(e2);
            return null;
        }
    }
}
