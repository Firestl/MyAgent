package supwisdom;

/* JADX INFO: compiled from: ParsableNalUnitBitArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class p80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f8777a;
    public int b;
    public int c;
    public int d;

    public p80(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.f8777a = bArr;
        this.c = i;
        this.b = i2;
        this.d = 0;
        f();
    }

    public boolean b(int i) {
        int i2 = this.c;
        int i3 = (i / 8) + i2;
        int i4 = this.d + (i % 8);
        if (i4 > 7) {
            i3++;
            i4 -= 8;
        }
        while (true) {
            i2++;
            if (i2 > i3 || i3 >= this.b) {
                break;
            }
            if (d(i2)) {
                i3++;
                i2 += 2;
            }
        }
        int i5 = this.b;
        if (i3 >= i5) {
            return i3 == i5 && i4 == 0;
        }
        return true;
    }

    public int c(int i) {
        int i2;
        int i3;
        if (i == 0) {
            return 0;
        }
        int i4 = i / 8;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = d(this.c + 1) ? this.c + 2 : this.c + 1;
            int i8 = this.d;
            if (i8 != 0) {
                byte[] bArr = this.f8777a;
                i3 = ((bArr[i7] & 255) >>> (8 - i8)) | ((bArr[this.c] & 255) << i8);
            } else {
                i3 = this.f8777a[this.c];
            }
            i -= 8;
            i5 |= (255 & i3) << i;
            this.c = i7;
        }
        if (i > 0) {
            int i9 = this.d + i;
            byte b = (byte) (255 >> (8 - i));
            int i10 = d(this.c + 1) ? this.c + 2 : this.c + 1;
            if (i9 > 8) {
                byte[] bArr2 = this.f8777a;
                i2 = (b & (((255 & bArr2[i10]) >> (16 - i9)) | ((bArr2[this.c] & 255) << (i9 - 8)))) | i5;
                this.c = i10;
            } else {
                i2 = (b & ((255 & this.f8777a[this.c]) >> (8 - i9))) | i5;
                if (i9 == 8) {
                    this.c = i10;
                }
            }
            i5 = i2;
            this.d = i9 % 8;
        }
        f();
        return i5;
    }

    public int d() {
        int iE = e();
        return (iE % 2 == 0 ? -1 : 1) * ((iE + 1) / 2);
    }

    public final int e() {
        int i = 0;
        while (!a()) {
            i++;
        }
        return ((1 << i) - 1) + (i > 0 ? c(i) : 0);
    }

    public final void f() {
        int i;
        int i2;
        int i3 = this.c;
        e80.b(i3 >= 0 && (i = this.d) >= 0 && i < 8 && (i3 < (i2 = this.b) || (i3 == i2 && i == 0)));
    }

    public final boolean d(int i) {
        if (2 <= i && i < this.b) {
            byte[] bArr = this.f8777a;
            if (bArr[i] == 3 && bArr[i - 2] == 0 && bArr[i - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    public void a(int i) {
        int i2 = this.c;
        int i3 = (i / 8) + i2;
        this.c = i3;
        int i4 = this.d + (i % 8);
        this.d = i4;
        if (i4 > 7) {
            this.c = i3 + 1;
            this.d = i4 - 8;
        }
        while (true) {
            i2++;
            if (i2 <= this.c) {
                if (d(i2)) {
                    this.c++;
                    i2 += 2;
                }
            } else {
                f();
                return;
            }
        }
    }

    public boolean b() {
        int i = this.c;
        int i2 = this.d;
        int i3 = 0;
        while (this.c < this.b && !a()) {
            i3++;
        }
        boolean z = this.c == this.b;
        this.c = i;
        this.d = i2;
        return !z && b((i3 * 2) + 1);
    }

    public boolean a() {
        return c(1) == 1;
    }

    public int c() {
        return e();
    }
}
