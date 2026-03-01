package supwisdom;

/* JADX INFO: compiled from: VorbisBitArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class t30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f9243a;
    public final int b;
    public int c;
    public int d;

    public t30(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public boolean a() {
        return a(1) == 1;
    }

    public void b(int i) {
        e80.b(b() + i <= this.b);
        int i2 = this.c + (i / 8);
        this.c = i2;
        int i3 = this.d + (i % 8);
        this.d = i3;
        if (i3 > 7) {
            this.c = i2 + 1;
            this.d = i3 - 8;
        }
    }

    public t30(byte[] bArr, int i) {
        this.f9243a = bArr;
        this.b = i * 8;
    }

    public int a(int i) {
        int iMin;
        int i2;
        e80.b(b() + i <= this.b);
        if (i == 0) {
            return 0;
        }
        int i3 = this.d;
        if (i3 != 0) {
            iMin = Math.min(i, 8 - i3);
            byte[] bArr = this.f9243a;
            int i4 = this.c;
            byte b = bArr[i4];
            int i5 = this.d;
            i2 = (255 >>> (8 - iMin)) & (b >>> i5);
            int i6 = i5 + iMin;
            this.d = i6;
            if (i6 == 8) {
                this.c = i4 + 1;
                this.d = 0;
            }
        } else {
            iMin = 0;
            i2 = 0;
        }
        int i7 = i - iMin;
        if (i7 > 7) {
            int i8 = i7 / 8;
            for (int i9 = 0; i9 < i8; i9++) {
                byte[] bArr2 = this.f9243a;
                int i10 = this.c;
                this.c = i10 + 1;
                i2 = (int) (((long) i2) | ((((long) bArr2[i10]) & 255) << iMin));
                iMin += 8;
            }
        }
        if (i <= iMin) {
            return i2;
        }
        int i11 = i - iMin;
        int i12 = i2 | (((255 >>> (8 - i11)) & this.f9243a[this.c]) << iMin);
        this.d += i11;
        return i12;
    }

    public int b() {
        return (this.c * 8) + this.d;
    }
}
