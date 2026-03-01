package com.umeng.analytics.pro;

/* JADX INFO: compiled from: TMemoryInputTransport.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dh extends di {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f5256a;
    public int b;
    public int c;

    public dh() {
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    @Override // com.umeng.analytics.pro.di
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.di
    public void b() throws dj {
    }

    @Override // com.umeng.analytics.pro.di
    public void b(byte[] bArr, int i, int i2) throws dj {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.analytics.pro.di
    public void c() {
    }

    public void c(byte[] bArr, int i, int i2) {
        this.f5256a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void e() {
        this.f5256a = null;
    }

    @Override // com.umeng.analytics.pro.di
    public byte[] f() {
        return this.f5256a;
    }

    @Override // com.umeng.analytics.pro.di
    public int g() {
        return this.b;
    }

    @Override // com.umeng.analytics.pro.di
    public int h() {
        return this.c - this.b;
    }

    public dh(byte[] bArr) {
        a(bArr);
    }

    @Override // com.umeng.analytics.pro.di
    public int a(byte[] bArr, int i, int i2) throws dj {
        int iH = h();
        if (i2 > iH) {
            i2 = iH;
        }
        if (i2 > 0) {
            System.arraycopy(this.f5256a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    public dh(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    @Override // com.umeng.analytics.pro.di
    public void a(int i) {
        this.b += i;
    }
}
