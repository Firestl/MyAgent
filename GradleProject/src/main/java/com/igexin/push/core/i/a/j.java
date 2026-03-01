package com.igexin.push.core.i.a;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3481a = 2;
    public static final int b = 10;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3482e = "GifHeaderParser";
    public static final int f = 255;
    public static final int g = 44;
    public static final int h = 33;
    public static final int i = 59;
    public static final int j = 249;
    public static final int k = 255;
    public static final int l = 254;
    public static final int m = 1;
    public static final int n = 28;
    public static final int o = 2;
    public static final int p = 1;
    public static final int q = 128;
    public static final int r = 64;
    public static final int s = 7;
    public static final int t = 128;
    public static final int u = 7;
    public static final int v = 256;
    public ByteBuffer c;
    public i d;
    public final byte[] w = new byte[256];
    public int x = 0;

    private j a(byte[] bArr) {
        if (bArr != null) {
            a(ByteBuffer.wrap(bArr));
        } else {
            this.c = null;
            this.d.d = 2;
        }
        return this;
    }

    private void a(int i2) {
        boolean z = false;
        while (!z && !p() && this.d.f3480e <= i2) {
            int iN = n();
            if (iN == 33) {
                int iN2 = n();
                if (iN2 != 1) {
                    if (iN2 == 249) {
                        this.d.f = new g();
                        f();
                    } else if (iN2 != 254 && iN2 == 255) {
                        m();
                        StringBuilder sb = new StringBuilder();
                        for (int i3 = 0; i3 < 11; i3++) {
                            sb.append((char) this.w[i3]);
                        }
                        if (sb.toString().equals("NETSCAPE2.0")) {
                            h();
                        }
                    }
                }
                l();
            } else if (iN == 44) {
                i iVar = this.d;
                if (iVar.f == null) {
                    iVar.f = new g();
                }
                g();
            } else if (iN != 59) {
                this.d.d = 1;
            } else {
                z = true;
            }
        }
    }

    private int[] b(int i2) {
        byte[] bArr = new byte[i2 * 3];
        int[] iArr = null;
        try {
            this.c.get(bArr);
            iArr = new int[256];
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = i3 + 1;
                iArr[i3] = ((bArr[i4] & 255) << 16) | (-16777216) | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
                i4 = i7;
                i3 = i8;
            }
        } catch (BufferUnderflowException e2) {
            com.igexin.c.a.c.a.b("GifHeaderParser", "Format Error Reading Color Table " + e2.getMessage());
            this.d.d = 1;
        }
        return iArr;
    }

    private void c() {
        this.c = null;
        Arrays.fill(this.w, (byte) 0);
        this.d = new i();
        this.x = 0;
    }

    private boolean d() {
        i();
        if (!p()) {
            a(2);
        }
        return this.d.f3480e > 1;
    }

    private void e() {
        a(Integer.MAX_VALUE);
    }

    private void f() {
        n();
        int iN = n();
        g gVar = this.d.f;
        int i2 = (iN & 28) >> 2;
        gVar.k = i2;
        if (i2 == 0) {
            gVar.k = 1;
        }
        this.d.f.j = (iN & 1) != 0;
        short s2 = this.c.getShort();
        if (s2 < 2) {
            s2 = 10;
        }
        g gVar2 = this.d.f;
        gVar2.m = s2 * 10;
        gVar2.l = n();
        n();
    }

    private void g() {
        this.d.f.f3474e = this.c.getShort();
        this.d.f.f = this.c.getShort();
        this.d.f.g = this.c.getShort();
        this.d.f.h = this.c.getShort();
        int iN = n();
        boolean z = (iN & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (iN & 7) + 1);
        this.d.f.i = (iN & 64) != 0;
        g gVar = this.d.f;
        if (z) {
            gVar.o = b(iPow);
        } else {
            gVar.o = null;
        }
        this.d.f.n = this.c.position();
        k();
        if (p()) {
            return;
        }
        i iVar = this.d;
        iVar.f3480e++;
        iVar.g.add(iVar.f);
    }

    private void h() {
        do {
            m();
            byte[] bArr = this.w;
            if (bArr[0] == 1) {
                this.d.o = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.x <= 0) {
                return;
            }
        } while (!p());
    }

    private void i() {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 6; i2++) {
            sb.append((char) n());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.d.d = 1;
            return;
        }
        j();
        if (!this.d.j || p()) {
            return;
        }
        i iVar = this.d;
        iVar.c = b(iVar.k);
        i iVar2 = this.d;
        iVar2.n = iVar2.c[iVar2.l];
    }

    private void j() {
        this.d.h = this.c.getShort();
        this.d.i = this.c.getShort();
        this.d.j = (n() & 128) != 0;
        this.d.k = (int) Math.pow(2.0d, (r0 & 7) + 1);
        this.d.l = n();
        this.d.m = n();
    }

    private void k() {
        n();
        l();
    }

    private void l() {
        int iN;
        do {
            iN = n();
            this.c.position(Math.min(this.c.position() + iN, this.c.limit()));
        } while (iN > 0);
    }

    private void m() {
        int iN = n();
        this.x = iN;
        if (iN > 0) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.x) {
                try {
                    i3 = this.x - i2;
                    this.c.get(this.w, i2, i3);
                    i2 += i3;
                } catch (Exception e2) {
                    com.igexin.c.a.c.a.b("GifHeaderParser", "Error Reading Block n: " + i2 + " count: " + i3 + " blockSize: " + this.x + e2.getMessage());
                    this.d.d = 1;
                    return;
                }
            }
        }
    }

    private int n() {
        try {
            return this.c.get() & 255;
        } catch (Exception unused) {
            this.d.d = 1;
            return 0;
        }
    }

    private int o() {
        return this.c.getShort();
    }

    private boolean p() {
        return this.d.d != 0;
    }

    public final j a(ByteBuffer byteBuffer) {
        c();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.c = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.c.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public final void a() {
        this.c = null;
        this.d = null;
    }

    public final i b() {
        if (this.c == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (p()) {
            return this.d;
        }
        i();
        if (!p()) {
            e();
            i iVar = this.d;
            if (iVar.f3480e < 0) {
                iVar.d = 1;
            }
        }
        return this.d;
    }
}
