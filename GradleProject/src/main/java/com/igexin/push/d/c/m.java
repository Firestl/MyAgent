package com.igexin.push.d.c;

import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3536a = 37;
    public boolean b;
    public boolean c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3537e;
    public long f;

    public m() {
        this.m = 37;
    }

    @Override // com.igexin.push.d.c.c, com.igexin.c.a.d.a.a
    public final void a() {
        this.f3537e = null;
        this.d = null;
        this.f = 0L;
    }

    @Override // com.igexin.push.d.c.c
    public final void a(byte[] bArr) {
        byte b = bArr[0];
        int i = 1;
        this.b = (b & UTF8.S_P3B) != 0;
        boolean z = (b & com.igexin.c.a.d.g.n) != 0;
        this.c = z;
        if (z) {
            this.d = c.a(b);
            int iB = com.igexin.c.a.b.g.b(bArr, 1);
            i = 1 + iB + 2;
            try {
                this.f3537e = new String(bArr, 3, iB, this.d);
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
        if (bArr.length > i) {
            this.f = com.igexin.c.a.b.g.d(bArr, i);
        }
    }

    @Override // com.igexin.push.d.c.c
    public final byte[] b() {
        int length;
        int i;
        byte bA = this.b ? UTF8.S_P3B : (byte) 0;
        int iB = 1;
        byte[] bytes = null;
        if (this.c) {
            byte b = (byte) (bA | com.igexin.c.a.d.g.n);
            try {
                bytes = this.f3537e.getBytes(this.d);
                length = bytes.length;
                i = length + 3;
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
                i = 3;
                length = 0;
            }
            bA = (byte) (b | c.a(this.d));
        } else {
            length = 0;
            i = 1;
        }
        byte[] bArr = new byte[i + 8];
        bArr[0] = bA;
        if (this.c) {
            iB = com.igexin.c.a.b.g.b(length, bArr, 1);
            if (bytes != null) {
                iB = com.igexin.c.a.b.g.a(bytes, bArr, 2, length) + 2;
            }
        }
        com.igexin.c.a.b.g.a(this.f, bArr, iB);
        return bArr;
    }
}
