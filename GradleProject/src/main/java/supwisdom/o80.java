package supwisdom;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: ParsableByteArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class o80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f8644a;
    public int b;
    public int c;

    public o80() {
    }

    public void a(int i) {
        a(e() < i ? new byte[i] : this.f8644a, i);
    }

    public int b() {
        return this.c - this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        byte[] bArr = this.f8644a;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public char f() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        return (char) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int g() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }

    public int h() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = (bArr[i] & 255) << 8;
        this.b = i2 + 1;
        return (bArr[i2] & 255) | i3;
    }

    public int i() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = bArr[i] & 255;
        this.b = i2 + 1;
        return ((bArr[i2] & 255) << 8) | i3;
    }

    public short j() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = (bArr[i] & 255) << 8;
        this.b = i2 + 1;
        return (short) ((bArr[i2] & 255) | i3);
    }

    public int k() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = (bArr[i] & 255) << 16;
        int i4 = i2 + 1;
        this.b = i4;
        int i5 = i3 | ((bArr[i2] & 255) << 8);
        this.b = i4 + 1;
        return (bArr[i4] & 255) | i5;
    }

    public long l() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        long j = (((long) bArr[i]) & 255) << 24;
        int i3 = i2 + 1;
        this.b = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 16);
        int i4 = i3 + 1;
        this.b = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 8);
        this.b = i4 + 1;
        return j3 | (((long) bArr[i4]) & 255);
    }

    public long m() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        long j = ((long) bArr[i]) & 255;
        int i3 = i2 + 1;
        this.b = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 8);
        int i4 = i3 + 1;
        this.b = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 16);
        this.b = i4 + 1;
        return j3 | ((((long) bArr[i4]) & 255) << 24);
    }

    public int n() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = (bArr[i] & 255) << 24;
        int i4 = i2 + 1;
        this.b = i4;
        int i5 = i3 | ((bArr[i2] & 255) << 16);
        int i6 = i4 + 1;
        this.b = i6;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        this.b = i6 + 1;
        return (bArr[i6] & 255) | i7;
    }

    public int o() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = bArr[i] & 255;
        int i4 = i2 + 1;
        this.b = i4;
        int i5 = i3 | ((bArr[i2] & 255) << 8);
        int i6 = i4 + 1;
        this.b = i6;
        int i7 = i5 | ((bArr[i4] & 255) << 16);
        this.b = i6 + 1;
        return ((bArr[i6] & 255) << 24) | i7;
    }

    public long p() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        long j = (((long) bArr[i]) & 255) << 56;
        int i3 = i2 + 1;
        this.b = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 48);
        int i4 = i3 + 1;
        this.b = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 40);
        int i5 = i4 + 1;
        this.b = i5;
        long j4 = j3 | ((((long) bArr[i4]) & 255) << 32);
        int i6 = i5 + 1;
        this.b = i6;
        long j5 = j4 | ((((long) bArr[i5]) & 255) << 24);
        int i7 = i6 + 1;
        this.b = i7;
        long j6 = j5 | ((((long) bArr[i6]) & 255) << 16);
        int i8 = i7 + 1;
        this.b = i8;
        long j7 = j6 | ((((long) bArr[i7]) & 255) << 8);
        this.b = i8 + 1;
        return j7 | (((long) bArr[i8]) & 255);
    }

    public long q() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        long j = ((long) bArr[i]) & 255;
        int i3 = i2 + 1;
        this.b = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 8);
        int i4 = i3 + 1;
        this.b = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 16);
        int i5 = i4 + 1;
        this.b = i5;
        long j4 = j3 | ((((long) bArr[i4]) & 255) << 24);
        int i6 = i5 + 1;
        this.b = i6;
        long j5 = j4 | ((((long) bArr[i5]) & 255) << 32);
        int i7 = i6 + 1;
        this.b = i7;
        long j6 = j5 | ((((long) bArr[i6]) & 255) << 40);
        int i8 = i7 + 1;
        this.b = i8;
        long j7 = j6 | ((((long) bArr[i7]) & 255) << 48);
        this.b = i8 + 1;
        return j7 | ((((long) bArr[i8]) & 255) << 56);
    }

    public int r() {
        byte[] bArr = this.f8644a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = (bArr[i] & 255) << 8;
        int i4 = i2 + 1;
        this.b = i4;
        int i5 = (bArr[i2] & 255) | i3;
        this.b = i4 + 2;
        return i5;
    }

    public int s() {
        return (g() << 21) | (g() << 14) | (g() << 7) | g();
    }

    public int t() {
        int iN = n();
        if (iN >= 0) {
            return iN;
        }
        throw new IllegalStateException("Top bit not zero: " + iN);
    }

    public int u() {
        int iO = o();
        if (iO >= 0) {
            return iO;
        }
        throw new IllegalStateException("Top bit not zero: " + iO);
    }

    public long v() {
        long jP = p();
        if (jP >= 0) {
            return jP;
        }
        throw new IllegalStateException("Top bit not zero: " + jP);
    }

    public double w() {
        return Double.longBitsToDouble(p());
    }

    public String x() {
        if (b() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && this.f8644a[i] != 0) {
            i++;
        }
        byte[] bArr = this.f8644a;
        int i2 = this.b;
        String str = new String(bArr, i2, i - i2);
        this.b = i;
        if (i < this.c) {
            this.b = i + 1;
        }
        return str;
    }

    public String y() {
        if (b() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && !x80.a(this.f8644a[i])) {
            i++;
        }
        int i2 = this.b;
        if (i - i2 >= 3) {
            byte[] bArr = this.f8644a;
            if (bArr[i2] == -17 && bArr[i2 + 1] == -69 && bArr[i2 + 2] == -65) {
                this.b = i2 + 3;
            }
        }
        byte[] bArr2 = this.f8644a;
        int i3 = this.b;
        String str = new String(bArr2, i3, i - i3);
        this.b = i;
        int i4 = this.c;
        if (i == i4) {
            return str;
        }
        if (this.f8644a[i] == 13) {
            int i5 = i + 1;
            this.b = i5;
            if (i5 == i4) {
                return str;
            }
        }
        byte[] bArr3 = this.f8644a;
        int i6 = this.b;
        if (bArr3[i6] == 10) {
            this.b = i6 + 1;
        }
        return str;
    }

    public long z() {
        int i;
        int i2;
        long j = this.f8644a[this.b];
        int i3 = 7;
        while (true) {
            if (i3 < 0) {
                break;
            }
            int i4 = 1 << i3;
            if ((((long) i4) & j) != 0) {
                i3--;
            } else if (i3 < 6) {
                j &= (long) (i4 - 1);
                i2 = 7 - i3;
            } else if (i3 == 7) {
                i2 = 1;
            }
        }
        i2 = 0;
        if (i2 == 0) {
            throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
        }
        for (i = 1; i < i2; i++) {
            byte b = this.f8644a[this.b + i];
            if ((b & 192) != 128) {
                throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
            }
            j = (j << 6) | ((long) (b & 63));
        }
        this.b += i2;
        return j;
    }

    public o80(int i) {
        this.f8644a = new byte[i];
        this.c = i;
    }

    public void a(byte[] bArr, int i) {
        this.f8644a = bArr;
        this.c = i;
        this.b = 0;
    }

    public void b(int i) {
        e80.a(i >= 0 && i <= this.f8644a.length);
        this.c = i;
    }

    public void c(int i) {
        e80.a(i >= 0 && i <= this.c);
        this.b = i;
    }

    public void d(int i) {
        c(this.b + i);
    }

    public String e(int i) {
        return a(i, Charset.defaultCharset());
    }

    public String f(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = (this.b + i) - 1;
        String str = new String(this.f8644a, this.b, (i2 >= this.c || this.f8644a[i2] != 0) ? i : i - 1);
        this.b += i;
        return str;
    }

    public o80(byte[] bArr) {
        this.f8644a = bArr;
        this.c = bArr.length;
    }

    public void a() {
        this.b = 0;
        this.c = 0;
    }

    public void a(n80 n80Var, int i) {
        a(n80Var.f8483a, 0, i);
        n80Var.a(0);
    }

    public o80(byte[] bArr, int i) {
        this.f8644a = bArr;
        this.c = i;
    }

    public void a(byte[] bArr, int i, int i2) {
        System.arraycopy(this.f8644a, this.b, bArr, i, i2);
        this.b += i2;
    }

    public String a(int i, Charset charset) {
        String str = new String(this.f8644a, this.b, i, charset);
        this.b += i;
        return str;
    }
}
