package com.umeng.analytics.pro;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: TCompactProtocol.java */
/* JADX INFO: loaded from: classes2.dex */
public class co extends cu {
    public static final cz d = new cz("");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final cp f5236e = new cp("", (byte) 0, 0);
    public static final byte[] f = {0, 0, 1, 3, 7, 0, 4, 0, 5, 0, 6, 8, 12, 11, 10, 9};
    public static final byte h = -126;
    public static final byte i = 1;
    public static final byte j = 31;
    public static final byte k = -32;
    public static final int l = 5;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f5237a;
    public byte[] b;
    public byte[] c;
    public bt m;
    public short n;
    public cp o;
    public Boolean p;
    public final long q;
    public byte[] r;

    /* JADX INFO: compiled from: TCompactProtocol.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final byte f5239a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final byte f5240e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;
    }

    public co(di diVar, long j2) {
        super(diVar);
        this.m = new bt(15);
        this.n = (short) 0;
        this.o = null;
        this.p = null;
        this.f5237a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = j2;
    }

    private int E() throws cb {
        int i2 = 0;
        if (this.g.h() >= 5) {
            byte[] bArrF = this.g.f();
            int iG = this.g.g();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte b2 = bArrF[iG + i2];
                i3 |= (b2 & 127) << i4;
                if ((b2 & com.igexin.c.a.d.g.n) != 128) {
                    this.g.a(i2 + 1);
                    return i3;
                }
                i4 += 7;
                i2++;
            }
        } else {
            int i5 = 0;
            while (true) {
                byte bU = u();
                i2 |= (bU & 127) << i5;
                if ((bU & com.igexin.c.a.d.g.n) != 128) {
                    return i2;
                }
                i5 += 7;
            }
        }
    }

    private long F() throws cb {
        int i2 = 0;
        long j2 = 0;
        if (this.g.h() >= 10) {
            byte[] bArrF = this.g.f();
            int iG = this.g.g();
            long j3 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = bArrF[iG + i2];
                j3 |= ((long) (b2 & 127)) << i3;
                if ((b2 & com.igexin.c.a.d.g.n) != 128) {
                    this.g.a(i2 + 1);
                    return j3;
                }
                i3 += 7;
                i2++;
            }
        } else {
            while (true) {
                byte bU = u();
                j2 |= ((long) (bU & 127)) << i2;
                if ((bU & com.igexin.c.a.d.g.n) != 128) {
                    return j2;
                }
                i2 += 7;
            }
        }
    }

    private int c(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    private long c(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    private boolean c(byte b2) {
        int i2 = b2 & 15;
        return i2 == 1 || i2 == 2;
    }

    private long d(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    private byte[] e(int i2) throws cb {
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        this.g.d(bArr, 0, i2);
        return bArr;
    }

    private void f(int i2) throws cv {
        if (i2 < 0) {
            throw new cv("Negative length: " + i2);
        }
        long j2 = this.q;
        if (j2 == -1 || i2 <= j2) {
            return;
        }
        throw new cv("Length exceeded max allowed: " + i2);
    }

    private int g(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    @Override // com.umeng.analytics.pro.cu
    public ByteBuffer A() throws cb {
        int iE = E();
        f(iE);
        if (iE == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[iE];
        this.g.d(bArr, 0, iE);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.umeng.analytics.pro.cu
    public void B() {
        this.m.c();
        this.n = (short) 0;
    }

    @Override // com.umeng.analytics.pro.cu
    public void a() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cs csVar) throws cb {
        b(h);
        d(((csVar.b << 5) & (-32)) | 1);
        b(csVar.c);
        a(csVar.f5244a);
    }

    @Override // com.umeng.analytics.pro.cu
    public void b() throws cb {
        this.n = this.m.a();
    }

    @Override // com.umeng.analytics.pro.cu
    public void c() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public void d() throws cb {
        b((byte) 0);
    }

    @Override // com.umeng.analytics.pro.cu
    public void e() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public void f() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public void g() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public cs h() throws cb {
        byte bU = u();
        if (bU != -126) {
            throw new cv("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(bU));
        }
        byte bU2 = u();
        byte b2 = (byte) (bU2 & j);
        if (b2 == 1) {
            return new cs(z(), (byte) ((bU2 >> 5) & 3), E());
        }
        throw new cv("Expected version 1 but got " + ((int) b2));
    }

    @Override // com.umeng.analytics.pro.cu
    public void i() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public cz j() throws cb {
        this.m.a(this.n);
        this.n = (short) 0;
        return d;
    }

    @Override // com.umeng.analytics.pro.cu
    public void k() throws cb {
        this.n = this.m.a();
    }

    @Override // com.umeng.analytics.pro.cu
    public cp l() throws cb {
        byte bU = u();
        if (bU == 0) {
            return f5236e;
        }
        short s = (short) ((bU & 240) >> 4);
        byte b2 = (byte) (bU & 15);
        cp cpVar = new cp("", d(b2), s == 0 ? v() : (short) (this.n + s));
        if (c(bU)) {
            this.p = b2 == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = cpVar.c;
        return cpVar;
    }

    @Override // com.umeng.analytics.pro.cu
    public void m() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public cr n() throws cb {
        int iE = E();
        byte bU = iE == 0 ? (byte) 0 : u();
        return new cr(d((byte) (bU >> 4)), d((byte) (bU & 15)), iE);
    }

    @Override // com.umeng.analytics.pro.cu
    public void o() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public cq p() throws cb {
        byte bU = u();
        int iE = (bU >> 4) & 15;
        if (iE == 15) {
            iE = E();
        }
        return new cq(d(bU), iE);
    }

    @Override // com.umeng.analytics.pro.cu
    public void q() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public cy r() throws cb {
        return new cy(p());
    }

    @Override // com.umeng.analytics.pro.cu
    public void s() throws cb {
    }

    @Override // com.umeng.analytics.pro.cu
    public boolean t() throws cb {
        Boolean bool = this.p;
        if (bool == null) {
            return u() == 1;
        }
        boolean zBooleanValue = bool.booleanValue();
        this.p = null;
        return zBooleanValue;
    }

    @Override // com.umeng.analytics.pro.cu
    public byte u() throws cb {
        if (this.g.h() <= 0) {
            this.g.d(this.c, 0, 1);
            return this.c[0];
        }
        byte b2 = this.g.f()[this.g.g()];
        this.g.a(1);
        return b2;
    }

    @Override // com.umeng.analytics.pro.cu
    public short v() throws cb {
        return (short) g(E());
    }

    @Override // com.umeng.analytics.pro.cu
    public int w() throws cb {
        return g(E());
    }

    @Override // com.umeng.analytics.pro.cu
    public long x() throws cb {
        return d(F());
    }

    @Override // com.umeng.analytics.pro.cu
    public double y() throws cb {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    @Override // com.umeng.analytics.pro.cu
    public String z() throws cb {
        int iE = E();
        f(iE);
        if (iE == 0) {
            return "";
        }
        try {
            if (this.g.h() < iE) {
                return new String(e(iE), "UTF-8");
            }
            String str = new String(this.g.f(), this.g.g(), iE, "UTF-8");
            this.g.a(iE);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new cb("UTF-8 not supported!");
        }
    }

    /* JADX INFO: compiled from: TCompactProtocol.java */
    public static class a implements cw {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f5238a;

        public a() {
            this.f5238a = -1L;
        }

        @Override // com.umeng.analytics.pro.cw
        public cu a(di diVar) {
            return new co(diVar, this.f5238a);
        }

        public a(int i) {
            this.f5238a = i;
        }
    }

    private void b(int i2) throws cb {
        int i3 = 0;
        while ((i2 & (-128)) != 0) {
            this.f5237a[i3] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
            i3++;
        }
        byte[] bArr = this.f5237a;
        bArr[i3] = (byte) i2;
        this.g.b(bArr, 0, i3 + 1);
    }

    private void d(int i2) throws cb {
        b((byte) i2);
    }

    private byte d(byte b2) throws cv {
        byte b3 = (byte) (b2 & 15);
        switch (b3) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return (byte) 15;
            case 10:
                return db.l;
            case 11:
                return (byte) 13;
            case 12:
                return (byte) 12;
            default:
                throw new cv("don't know what type: " + ((int) b3));
        }
    }

    private byte e(byte b2) {
        return f[b2];
    }

    private void b(long j2) throws cb {
        int i2 = 0;
        while (((-128) & j2) != 0) {
            this.b[i2] = (byte) ((127 & j2) | 128);
            j2 >>>= 7;
            i2++;
        }
        byte[] bArr = this.b;
        bArr[i2] = (byte) j2;
        this.g.b(bArr, 0, i2 + 1);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cz czVar) throws cb {
        this.m.a(this.n);
        this.n = (short) 0;
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cp cpVar) throws cb {
        if (cpVar.b == 2) {
            this.o = cpVar;
        } else {
            a(cpVar, (byte) -1);
        }
    }

    private void b(byte b2) throws cb {
        byte[] bArr = this.r;
        bArr[0] = b2;
        this.g.b(bArr);
    }

    private void a(cp cpVar, byte b2) throws cb {
        if (b2 == -1) {
            b2 = e(cpVar.b);
        }
        short s = cpVar.c;
        short s2 = this.n;
        if (s > s2 && s - s2 <= 15) {
            d(b2 | ((s - s2) << 4));
        } else {
            b(b2);
            a(cpVar.c);
        }
        this.n = cpVar.c;
    }

    public co(di diVar) {
        this(diVar, -1L);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cr crVar) throws cb {
        int i2 = crVar.c;
        if (i2 == 0) {
            d(0);
            return;
        }
        b(i2);
        d(e(crVar.b) | (e(crVar.f5243a) << 4));
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cq cqVar) throws cb {
        a(cqVar.f5242a, cqVar.b);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cy cyVar) throws cb {
        a(cyVar.f5249a, cyVar.b);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(boolean z) throws cb {
        cp cpVar = this.o;
        if (cpVar != null) {
            a(cpVar, z ? (byte) 1 : (byte) 2);
            this.o = null;
        } else {
            b(z ? (byte) 1 : (byte) 2);
        }
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(byte b2) throws cb {
        b(b2);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(short s) throws cb {
        b(c((int) s));
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(int i2) throws cb {
        b(c(i2));
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(long j2) throws cb {
        b(c(j2));
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(double d2) throws cb {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        a(Double.doubleToLongBits(d2), bArr, 0);
        this.g.b(bArr);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(String str) throws cb {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new cb("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(ByteBuffer byteBuffer) throws cb {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i2, int i3) throws cb {
        b(i3);
        this.g.b(bArr, i2, i3);
    }

    public void a(byte b2, int i2) throws cb {
        if (i2 <= 14) {
            d(e(b2) | (i2 << 4));
        } else {
            d(e(b2) | 240);
            b(i2);
        }
    }

    private void a(long j2, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (j2 & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 48) & 255);
        bArr[i2 + 7] = (byte) ((j2 >> 56) & 255);
    }

    private long a(byte[] bArr) {
        return ((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (255 & ((long) bArr[0]));
    }
}
