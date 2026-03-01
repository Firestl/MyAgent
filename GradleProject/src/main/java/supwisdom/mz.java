package supwisdom;

import com.google.zxing.pdf417.PDF417Common;

/* JADX INFO: loaded from: classes.dex */
public final class mz {
    public static final mz f = new mz(PDF417Common.NUMBER_OF_CODEWORDS, 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f8454a;
    public final int[] b;
    public final nz c;
    public final nz d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8455e;

    public mz(int i, int i2) {
        this.f8455e = i;
        this.f8454a = new int[i];
        this.b = new int[i];
        int i3 = 1;
        for (int i4 = 0; i4 < i; i4++) {
            this.f8454a[i4] = i3;
            i3 = (i3 * i2) % i;
        }
        for (int i5 = 0; i5 < i - 1; i5++) {
            this.b[this.f8454a[i5]] = i5;
        }
        this.c = new nz(this, new int[]{0});
        this.d = new nz(this, new int[]{1});
    }

    public int a(int i, int i2) {
        return (i + i2) % this.f8455e;
    }

    public nz b(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.c;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new nz(this, iArr);
    }

    public nz c() {
        return this.c;
    }

    public int d(int i, int i2) {
        int i3 = this.f8455e;
        return ((i + i3) - i2) % i3;
    }

    public int a(int i) {
        return this.f8454a[i];
    }

    public int c(int i) {
        if (i != 0) {
            return this.b[i];
        }
        throw new IllegalArgumentException();
    }

    public nz a() {
        return this.d;
    }

    public int c(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.f8454a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.f8455e - 1)];
    }

    public int b() {
        return this.f8455e;
    }

    public int b(int i) {
        if (i != 0) {
            return this.f8454a[(this.f8455e - this.b[i]) - 1];
        }
        throw new ArithmeticException();
    }
}
