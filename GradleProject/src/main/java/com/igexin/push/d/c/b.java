package com.igexin.push.d.c;

/* JADX INFO: loaded from: classes2.dex */
public class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3520a = 25;
    public int b;
    public int c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3521e;
    public Object f;
    public Object g;
    public String h = "";
    public String i = "UTF-8";
    public String j = "none";
    public String k = "none";
    public String l = "none";

    public b() {
        this.m = 25;
        this.n = (byte) 20;
    }

    private boolean d() {
        return this.c == 128;
    }

    private boolean e() {
        return this.c == 64;
    }

    private boolean f() {
        return this.c == 192;
    }

    private void g() {
        this.c = 128;
    }

    private void h() {
        this.c = 64;
    }

    private void k() {
        this.c = 192;
    }

    @Override // com.igexin.push.d.c.c, com.igexin.c.a.d.a.a
    public final void a() {
        this.f3521e = null;
        this.f = null;
        this.g = null;
        this.i = null;
    }

    @Override // com.igexin.push.d.c.c
    public final void a(byte[] bArr) {
        int i;
        int i2;
        this.b = com.igexin.c.a.b.g.b(bArr, 0);
        this.c = bArr[2] & 192;
        this.i = c.a(bArr[2]);
        this.d = com.igexin.c.a.b.g.d(bArr, 3);
        int i3 = bArr[11] & 255;
        try {
            this.f3521e = new String(bArr, 12, i3, this.i);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        int i4 = i3 + 12;
        int i5 = 0;
        while (true) {
            i = i5 | (bArr[i4] & 127);
            if ((bArr[i4] & com.igexin.c.a.d.g.n) == 0) {
                break;
            }
            i5 = i << 7;
            i4++;
        }
        int i6 = i4 + 1;
        if (i > 0) {
            if (this.c == 192) {
                byte[] bArr2 = new byte[i];
                this.f = bArr2;
                System.arraycopy(bArr, i6, bArr2, 0, i);
            } else {
                try {
                    this.f = new String(bArr, i6, i, this.i);
                } catch (Exception e3) {
                    com.igexin.c.a.c.a.a(e3);
                }
            }
        }
        int i7 = i6 + i;
        int i8 = 0;
        while (true) {
            i2 = i8 | (bArr[i7] & 127);
            if ((bArr[i7] & com.igexin.c.a.d.g.n) == 0) {
                break;
            }
            i8 = i2 << 7;
            i7++;
        }
        int i9 = i7 + 1;
        if (i2 > 0) {
            byte[] bArr3 = new byte[i2];
            this.g = bArr3;
            System.arraycopy(bArr, i9, bArr3, 0, i2);
        }
        int i10 = i9 + i2;
        if (bArr.length > i10) {
            try {
                this.h = new String(bArr, i10 + 1, bArr[i10] & 255, this.i);
            } catch (Exception e4) {
                com.igexin.c.a.c.a.a(e4);
            }
        }
    }

    @Override // com.igexin.push.d.c.c
    public final byte[] b() {
        byte[] bArr = null;
        try {
            byte[] bytes = this.f3521e.getBytes(this.i);
            byte[] bytes2 = !"".equals(this.f) ? this.c == 192 ? (byte[]) this.f : ((String) this.f).getBytes(this.i) : null;
            byte[] bArr2 = this.g != null ? (byte[]) this.g : null;
            byte[] bytes3 = this.h.getBytes(this.i);
            int length = bytes2 == null ? 0 : bytes2.length;
            int length2 = bArr2 == null ? 0 : bArr2.length;
            byte[] bArrA = com.igexin.c.a.b.g.a(length);
            byte[] bArrA2 = com.igexin.c.a.b.g.a(length2);
            bArr = new byte[bytes.length + 13 + bArrA.length + length + bArrA2.length + length2 + bytes3.length];
            com.igexin.c.a.b.g.b(this.b, bArr, 0);
            bArr[2] = (byte) (this.c | c.a(this.i));
            com.igexin.c.a.b.g.a(this.d, bArr, 3);
            bArr[11] = (byte) bytes.length;
            int iA = com.igexin.c.a.b.g.a(bytes, bArr, 12, bytes.length) + 12;
            int iA2 = iA + com.igexin.c.a.b.g.a(bArrA, bArr, iA, bArrA.length);
            if (length > 0) {
                iA2 += com.igexin.c.a.b.g.a(bytes2, bArr, iA2, length);
            }
            int iA3 = iA2 + com.igexin.c.a.b.g.a(bArrA2, bArr, iA2, bArrA2.length);
            if (length2 > 0) {
                iA3 += com.igexin.c.a.b.g.a(bArr2, bArr, iA3, length2);
            }
            bArr[iA3] = (byte) bytes3.length;
            com.igexin.c.a.b.g.a(bytes3, bArr, iA3 + 1, bytes3.length);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        if (bArr != null && bArr.length >= com.igexin.push.config.d.O) {
            this.n = (byte) (this.n | com.igexin.c.a.d.g.n);
        }
        return bArr;
    }
}
