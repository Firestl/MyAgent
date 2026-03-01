package supwisdom;

/* JADX INFO: compiled from: ParsableBitArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class n80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f8483a;
    public int b;
    public int c;
    public int d;

    public n80() {
    }

    public void a(byte[] bArr, int i) {
        this.f8483a = bArr;
        this.b = 0;
        this.c = 0;
        this.d = i;
    }

    public int b() {
        return (this.b * 8) + this.c;
    }

    public int c() {
        e80.b(this.c == 0);
        return this.b;
    }

    public boolean d() {
        return c(1) == 1;
    }

    public void e() {
        if (this.c == 0) {
            return;
        }
        this.c = 0;
        this.b++;
        f();
    }

    public final void f() {
        int i;
        int i2;
        int i3 = this.b;
        e80.b(i3 >= 0 && (i = this.c) >= 0 && i < 8 && (i3 < (i2 = this.d) || (i3 == i2 && i == 0)));
    }

    public n80(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public void b(int i) {
        int i2 = this.b + (i / 8);
        this.b = i2;
        int i3 = this.c + (i % 8);
        this.c = i3;
        if (i3 > 7) {
            this.b = i2 + 1;
            this.c = i3 - 8;
        }
        f();
    }

    public void d(int i) {
        e80.b(this.c == 0);
        this.b += i;
        f();
    }

    public n80(byte[] bArr, int i) {
        this.f8483a = bArr;
        this.d = i;
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
            int i7 = this.c;
            if (i7 != 0) {
                byte[] bArr = this.f8483a;
                int i8 = this.b;
                i3 = ((bArr[i8 + 1] & 255) >>> (8 - i7)) | ((bArr[i8] & 255) << i7);
            } else {
                i3 = this.f8483a[this.b];
            }
            i -= 8;
            i5 |= (255 & i3) << i;
            this.b++;
        }
        if (i > 0) {
            int i9 = this.c + i;
            byte b = (byte) (255 >> (8 - i));
            if (i9 > 8) {
                byte[] bArr2 = this.f8483a;
                int i10 = this.b;
                i2 = (b & (((bArr2[i10 + 1] & 255) >> (16 - i9)) | ((bArr2[i10] & 255) << (i9 - 8)))) | i5;
                this.b = i10 + 1;
            } else {
                byte[] bArr3 = this.f8483a;
                int i11 = this.b;
                i2 = (b & ((bArr3[i11] & 255) >> (8 - i9))) | i5;
                if (i9 == 8) {
                    this.b = i11 + 1;
                }
            }
            i5 = i2;
            this.c = i9 % 8;
        }
        f();
        return i5;
    }

    public int a() {
        return ((this.d - this.b) * 8) - this.c;
    }

    public void a(int i) {
        int i2 = i / 8;
        this.b = i2;
        this.c = i - (i2 * 8);
        f();
    }

    public void a(byte[] bArr, int i, int i2) {
        e80.b(this.c == 0);
        System.arraycopy(this.f8483a, this.b, bArr, i, i2);
        this.b += i2;
        f();
    }
}
