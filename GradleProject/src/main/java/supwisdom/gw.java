package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class gw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f7777a;
    public int b;
    public int c;

    public gw(byte[] bArr) {
        this.f7777a = bArr;
    }

    public int a() {
        return ((this.f7777a.length - this.b) * 8) - this.c;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public int a(int i) {
        if (i < 1 || i > 32 || i > a()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i2 = this.c;
        int i3 = 0;
        if (i2 > 0) {
            int i4 = 8 - i2;
            int i5 = i < i4 ? i : i4;
            int i6 = i4 - i5;
            byte[] bArr = this.f7777a;
            int i7 = this.b;
            int i8 = (((255 >> (8 - i5)) << i6) & bArr[i7]) >> i6;
            i -= i5;
            int i9 = i2 + i5;
            this.c = i9;
            if (i9 == 8) {
                this.c = 0;
                this.b = i7 + 1;
            }
            i3 = i8;
        }
        if (i <= 0) {
            return i3;
        }
        while (i >= 8) {
            int i10 = i3 << 8;
            byte[] bArr2 = this.f7777a;
            int i11 = this.b;
            i3 = (bArr2[i11] & 255) | i10;
            this.b = i11 + 1;
            i -= 8;
        }
        if (i <= 0) {
            return i3;
        }
        int i12 = 8 - i;
        int i13 = (i3 << i) | ((((255 >> i12) << i12) & this.f7777a[this.b]) >> i12);
        this.c += i;
        return i13;
    }
}
