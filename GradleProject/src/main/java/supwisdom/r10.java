package supwisdom;

import dc.squareup.okhttp3.internal.platform.AndroidPlatform;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: Sonic.java */
/* JADX INFO: loaded from: classes.dex */
public final class r10 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8997a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8998e;
    public final short[] f;
    public int g;
    public short[] h;
    public int i;
    public short[] j;
    public int k;
    public short[] l;
    public int q;
    public int r;
    public int s;
    public int t;
    public int v;
    public int w;
    public int x;
    public int m = 0;
    public int n = 0;
    public int u = 0;
    public float o = 1.0f;
    public float p = 1.0f;

    public r10(int i, int i2) {
        this.f8997a = i;
        this.b = i2;
        this.c = i / 400;
        int i3 = i / 65;
        this.d = i3;
        int i4 = i3 * 2;
        this.f8998e = i4;
        this.f = new short[i4];
        this.g = i4;
        this.h = new short[i4 * i2];
        this.i = i4;
        this.j = new short[i4 * i2];
        this.k = i4;
        this.l = new short[i4 * i2];
    }

    public void a(float f) {
        this.o = f;
    }

    public void b(float f) {
        this.p = f;
    }

    public final void c(int i) {
        int i2 = this.q - i;
        short[] sArr = this.h;
        int i3 = this.b;
        System.arraycopy(sArr, i * i3, sArr, 0, i3 * i2);
        this.q = i2;
    }

    public final int d(int i) {
        int iMin = Math.min(this.f8998e, this.t);
        a(this.h, i, iMin);
        this.t -= iMin;
        return iMin;
    }

    public final void e(int i) {
        int i2 = this.r - i;
        int i3 = this.s + i2;
        int i4 = this.k;
        if (i3 > i4) {
            int i5 = i4 + (i4 / 2) + i2;
            this.k = i5;
            this.l = Arrays.copyOf(this.l, i5 * this.b);
        }
        short[] sArr = this.j;
        int i6 = this.b;
        System.arraycopy(sArr, i * i6, this.l, this.s * i6, i6 * i2);
        this.r = i;
        this.s += i2;
    }

    public final void f(int i) {
        if (i == 0) {
            return;
        }
        short[] sArr = this.l;
        int i2 = this.b;
        System.arraycopy(sArr, i * i2, sArr, 0, (this.s - i) * i2);
        this.s -= i;
    }

    public void a(ShortBuffer shortBuffer) {
        int iRemaining = shortBuffer.remaining();
        int i = this.b;
        int i2 = iRemaining / i;
        b(i2);
        shortBuffer.get(this.h, this.q * this.b, ((i * i2) * 2) / 2);
        this.q += i2;
        c();
    }

    public void b(ShortBuffer shortBuffer) {
        int iMin = Math.min(shortBuffer.remaining() / this.b, this.r);
        shortBuffer.put(this.j, 0, this.b * iMin);
        int i = this.r - iMin;
        this.r = i;
        short[] sArr = this.j;
        int i2 = this.b;
        System.arraycopy(sArr, iMin * i2, sArr, 0, i * i2);
    }

    public final void c(float f) {
        int iB;
        int i = this.q;
        if (i < this.f8998e) {
            return;
        }
        int i2 = 0;
        do {
            if (this.t > 0) {
                iB = d(i2);
            } else {
                int iA = a(this.h, i2, true);
                if (f > 1.0d) {
                    iB = iA + a(this.h, i2, f, iA);
                } else {
                    iB = b(this.h, i2, f, iA);
                }
            }
            i2 += iB;
        } while (this.f8998e + i2 <= i);
        c(i2);
    }

    public int b() {
        return this.r;
    }

    public void a() {
        int i;
        int i2 = this.q;
        float f = this.o;
        float f2 = this.p;
        int i3 = this.r + ((int) ((((i2 / (f / f2)) + this.s) / f2) + 0.5f));
        b((this.f8998e * 2) + i2);
        int i4 = 0;
        while (true) {
            i = this.f8998e;
            int i5 = this.b;
            if (i4 >= i * 2 * i5) {
                break;
            }
            this.h[(i5 * i2) + i4] = 0;
            i4++;
        }
        this.q += i * 2;
        c();
        if (this.r > i3) {
            this.r = i3;
        }
        this.q = 0;
        this.t = 0;
        this.s = 0;
    }

    public final void b(int i) {
        int i2 = this.q + i;
        int i3 = this.g;
        if (i2 > i3) {
            int i4 = i3 + (i3 / 2) + i;
            this.g = i4;
            this.h = Arrays.copyOf(this.h, i4 * this.b);
        }
    }

    public final void b(short[] sArr, int i, int i2) {
        int i3 = this.f8998e / i2;
        int i4 = this.b;
        int i5 = i2 * i4;
        int i6 = i * i4;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i5; i9++) {
                i8 += sArr[(i7 * i5) + i6 + i9];
            }
            this.f[i7] = (short) (i8 / i5);
        }
    }

    public final void c() {
        int i = this.r;
        float f = this.o / this.p;
        double d = f;
        if (d <= 1.00001d && d >= 0.99999d) {
            a(this.h, 0, this.q);
            this.q = 0;
        } else {
            c(f);
        }
        float f2 = this.p;
        if (f2 != 1.0f) {
            a(f2, i);
        }
    }

    public final short b(short[] sArr, int i, int i2, int i3) {
        short s = sArr[i];
        short s2 = sArr[i + this.b];
        int i4 = this.n * i2;
        int i5 = this.m;
        int i6 = i5 * i3;
        int i7 = (i5 + 1) * i3;
        int i8 = i7 - i4;
        int i9 = i7 - i6;
        return (short) (((s * i8) + ((i9 - i8) * s2)) / i9);
    }

    public final void a(int i) {
        int i2 = this.r + i;
        int i3 = this.i;
        if (i2 > i3) {
            int i4 = i3 + (i3 / 2) + i;
            this.i = i4;
            this.j = Arrays.copyOf(this.j, i4 * this.b);
        }
    }

    public final int b(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f < 0.5f) {
            i3 = (int) ((i2 * f) / (1.0f - f));
        } else {
            this.t = (int) ((i2 * ((2.0f * f) - 1.0f)) / (1.0f - f));
            i3 = i2;
        }
        int i4 = i2 + i3;
        a(i4);
        int i5 = this.b;
        System.arraycopy(sArr, i * i5, this.j, this.r * i5, i5 * i2);
        a(i3, this.b, this.j, this.r + i2, sArr, i + i2, sArr, i);
        this.r += i4;
        return i3;
    }

    public final void a(short[] sArr, int i, int i2) {
        a(i2);
        int i3 = this.b;
        System.arraycopy(sArr, i * i3, this.j, this.r * i3, i3 * i2);
        this.r += i2;
    }

    public final int a(short[] sArr, int i, int i2, int i3) {
        int i4 = i * this.b;
        int i5 = 1;
        int i6 = 255;
        int i7 = 0;
        int i8 = 0;
        while (i2 <= i3) {
            int i9 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                short s = sArr[i4 + i10];
                short s2 = sArr[i4 + i2 + i10];
                i9 += s >= s2 ? s - s2 : s2 - s;
            }
            if (i9 * i7 < i5 * i2) {
                i7 = i2;
                i5 = i9;
            }
            if (i9 * i6 > i8 * i2) {
                i6 = i2;
                i8 = i9;
            }
            i2++;
        }
        this.w = i5 / i7;
        this.x = i8 / i6;
        return i7;
    }

    public final boolean a(int i, int i2, boolean z) {
        if (i == 0 || this.u == 0) {
            return false;
        }
        return z ? i2 <= i * 3 && i * 2 > this.v * 3 : i > this.v;
    }

    public final int a(short[] sArr, int i, boolean z) {
        int iA;
        int i2 = this.f8997a;
        int i3 = i2 > 4000 ? i2 / AndroidPlatform.MAX_LOG_LENGTH : 1;
        if (this.b == 1 && i3 == 1) {
            iA = a(sArr, i, this.c, this.d);
        } else {
            b(sArr, i, i3);
            int iA2 = a(this.f, 0, this.c / i3, this.d / i3);
            if (i3 != 1) {
                int i4 = iA2 * i3;
                int i5 = i3 * 4;
                int i6 = i4 - i5;
                int i7 = i4 + i5;
                int i8 = this.c;
                if (i6 < i8) {
                    i6 = i8;
                }
                int i9 = this.d;
                if (i7 > i9) {
                    i7 = i9;
                }
                if (this.b == 1) {
                    iA = a(sArr, i, i6, i7);
                } else {
                    b(sArr, i, 1);
                    iA = a(this.f, 0, i6, i7);
                }
            } else {
                iA = iA2;
            }
        }
        int i10 = a(this.w, this.x, z) ? this.u : iA;
        this.v = this.w;
        this.u = iA;
        return i10;
    }

    public final void a(float f, int i) {
        int i2;
        int i3;
        if (this.r == i) {
            return;
        }
        int i4 = this.f8997a;
        int i5 = (int) (i4 / f);
        while (true) {
            if (i5 <= 16384 && i4 <= 16384) {
                break;
            }
            i5 /= 2;
            i4 /= 2;
        }
        e(i);
        int i6 = 0;
        while (true) {
            int i7 = this.s;
            if (i6 < i7 - 1) {
                while (true) {
                    i2 = this.m;
                    int i8 = (i2 + 1) * i5;
                    i3 = this.n;
                    if (i8 <= i3 * i4) {
                        break;
                    }
                    a(1);
                    int i9 = 0;
                    while (true) {
                        int i10 = this.b;
                        if (i9 < i10) {
                            this.j[(this.r * i10) + i9] = b(this.l, (i10 * i6) + i9, i4, i5);
                            i9++;
                        }
                    }
                    this.n++;
                    this.r++;
                }
                int i11 = i2 + 1;
                this.m = i11;
                if (i11 == i4) {
                    this.m = 0;
                    e80.b(i3 == i5);
                    this.n = 0;
                }
                i6++;
            } else {
                f(i7 - 1);
                return;
            }
        }
    }

    public final int a(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f >= 2.0f) {
            i3 = (int) (i2 / (f - 1.0f));
        } else {
            this.t = (int) ((i2 * (2.0f - f)) / (f - 1.0f));
            i3 = i2;
        }
        a(i3);
        a(i3, this.b, this.j, this.r, sArr, i, sArr, i + i2);
        this.r += i3;
        return i3;
    }

    public static void a(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i3 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i4 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i7] = (short) (((sArr2[i9] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i7 += i2;
                i9 += i2;
                i8 += i2;
            }
        }
    }
}
