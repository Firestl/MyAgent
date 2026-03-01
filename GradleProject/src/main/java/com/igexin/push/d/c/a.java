package com.igexin.push.d.c;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3518a;
    public byte b;
    public byte c;
    public byte d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f3519e;
    public int f;
    public byte g;

    private byte[] a() {
        if (this.f3519e == null) {
            return null;
        }
        byte[] bArr = new byte[this.f3518a + 11];
        com.igexin.c.a.b.g.a(com.igexin.push.g.g.e(), bArr, 0);
        com.igexin.c.a.b.g.a((int) (System.currentTimeMillis() / 1000), bArr, 4);
        com.igexin.c.a.b.g.b(this.f3518a, bArr, 8);
        bArr[10] = this.b;
        byte[] bArr2 = this.f3519e;
        com.igexin.c.a.b.g.a(bArr2, bArr, 11, bArr2.length);
        return bArr;
    }

    public final void a(byte[] bArr) {
        int length;
        if (bArr == null) {
            length = 0;
        } else {
            this.f3519e = bArr;
            length = bArr.length;
        }
        this.f3518a = length;
    }
}
